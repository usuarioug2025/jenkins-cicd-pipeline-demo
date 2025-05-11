pipeline {
    agent any

    tools {
        // Configurar JDK en Jenkins
        jdk 'jdk17'
    }

    stages {
        stage('Checkout') {
            steps {
                // Obtener código del repositorio
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Dar permisos de ejecución al script de Maven
                sh 'chmod +x mvnw'
                // Compilar el proyecto con Maven
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                // Ejecutar pruebas
                sh './mvnw test'
            }
            post {
                // Publicar resultados de pruebas
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                // Construir imagen Docker
                sh 'docker build -t jenkins-demo-app:latest .'
            }
        }

        stage('Deploy') {
            steps {
                // Detener contenedor anterior si existe
                sh 'docker stop jenkins-demo-app || true'
                sh 'docker rm jenkins-demo-app || true'

                // Ejecutar nuevo contenedor
                sh 'docker run -d -p 8888:8080 --name jenkins-demo-app jenkins-demo-app:latest'

                echo 'Application deployed successfully at http://localhost:8888'
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline execution failed!'
        }
    }
}
