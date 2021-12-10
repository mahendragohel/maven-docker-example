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

        stage("Generate Helm chart and Upload to Nexus"){
            steps{
                sh "helm package target/classes/helm"
                sh "curl -v -F file=@springboot-0.1.0.tgz -u admin:admin http://localhost:8081/service/rest/v1/components?repository==helm-release"
            }
        }
    }
}