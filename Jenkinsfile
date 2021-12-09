pipeline {
    agent any

    tools {
        maven 'maven-3.8.4'
    }
    triggers {
        pollSCM '* * * * *'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
                sh "helm package target/classes/helm"
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }

            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
        stage('Build Docker Image'){
            steps{
                sh "docker build -t mahendragohel/kafka-example:${env.BUILD_ID} ."
            }
        }
        stage('Push Docker image') {
            environment {
                DOCKER_HUB_LOGIN = credentials('docker-hub')
            }
            steps {
                sh 'docker login --username=$DOCKER_HUB_LOGIN_USR --password=$DOCKER_HUB_LOGIN_PSW'
                sh "docker push mahendragohel/kafka-example:${env.BUILD_ID}"
            }
        }
    }
}