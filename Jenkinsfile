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

        stage('Build Docker Image (Simulation)') {
            steps {
                // Simulación de construcción de imagen Docker
                echo 'Simulando construcción de imagen Docker...'
                echo 'docker build -t jenkins-demo-app:latest .'
                // Ver el contenido del Dockerfile para verificar
                sh 'cat Dockerfile'
            }
        }

        stage('Deploy (Simulation)') {
            steps {
                // Simulación de despliegue
                echo 'Simulando despliegue de la aplicación...'
                echo 'Deteniendo contenedor anterior si existe: docker stop jenkins-demo-app || true'
                echo 'Eliminando contenedor anterior si existe: docker rm jenkins-demo-app || true'
                echo 'Desplegando nuevo contenedor: docker run -d -p 8888:8080 --name jenkins-demo-app jenkins-demo-app:latest'

                echo 'Aplicación desplegada (simulación) correctamente en http://localhost:8888'
            }
        }
    }

    post {
        success {
            echo 'Pipeline ejecutado exitosamente!'
        }
        failure {
            echo 'Pipeline falló en su ejecución!'
        }
    }
}