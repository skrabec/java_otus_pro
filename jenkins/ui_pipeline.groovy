pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Validate Dockerfile') {
            steps {
                script {
                    // Check if Dockerfile exists
                    if (!fileExists('Dockerfile')) {
                        error "Dockerfile not found in the repository root"
                    }

                    // Read Dockerfile content
                    def dockerfile = readFile('Dockerfile')

                    // Check for FROM instruction
                    if (!dockerfile.toLowerCase().trim().contains('from ')) {
                        error "Dockerfile validation failed: No source image provided with FROM instruction"
                    }

                    echo "Dockerfile validation passed"
                }
            }
        }

        stage('Run Tests') {


            status = sh (
                script: 'mvn test -DBROWSER=$env.BROWSER -DBASE_URL=$env.BASE_URL',
                returnStatus: true
            )

            if (status > 0) {
                currentBuild.status = 'UNSTABLE'
            }

        }

        stage('Publish allure report') {
            allure([
                disabled: true,
                includeProperties: false,
                jdk: '',
                report: './target/allure-results',
                reportBuildPolicy: 'ALWAYS'
            ])

        }
    }

    post {
        always {
            cleanWs()
        }
        failure {
            echo 'Pipeline failed during Dockerfile validation'
        }
    }
}