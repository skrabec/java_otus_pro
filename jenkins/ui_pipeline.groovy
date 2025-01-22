pipeline {
    agent any

    environment {
        BROWSER = 'chrome'
        BASE_URL = 'https://otus.ru'
        SELENOID_URL = 'http://45.132.17.22:4444/wd/hub/'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Validate Dockerfile') {
            steps {
                script {
                    if (!fileExists('Dockerfile')) {
                        error "Dockerfile not found in the repository root"
                    }

                    def dockerfile = readFile('Dockerfile')
                    if (!dockerfile.toLowerCase().trim().contains('from ')) {
                        error "Dockerfile validation failed: No source image provided with FROM instruction"
                    }

                    echo "Dockerfile validation passed"
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    def status = sh(
                            script: """
                            mvn clean test \
                            -DBROWSER=${env.BROWSER} \
                            -DBASE_URL=${env.BASE_URL} \
                            -Dremote.url=http://45.132.17.22:4444/wd/hub/ \
                            -Dallure.results.directory=target/allure-results
                        """,
                            returnStatus: true
                    )

                    if (status > 0) {
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Verify Allure Results') {
            steps {
                sh 'ls -la target/allure-results || true'
            }
        }

        stage('Publish allure report') {
            steps {
                script {
                    allure([
                            includeProperties: true,
                            jdk: '',
                            properties: [],
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'target/allure-results']],
                            report: 'target/allure-report'
                    ])
                }
            }
        }
    }

    post {
        always {
            script {
                try {
                    archiveArtifacts artifacts: 'target/allure-results/**'
                } catch (Exception e) {
                    echo "Failed to archive Allure results: ${e.message}"
                }
                cleanWs()
            }
        }
        failure {
            echo 'Pipeline failed during execution'
        }
    }
}