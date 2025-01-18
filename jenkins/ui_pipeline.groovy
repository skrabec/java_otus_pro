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
            steps {
                sh 'mvn clean test'
            }
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