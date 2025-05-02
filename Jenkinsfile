pipeline {
    agent any

    tools {
        jdk 'JDK_21'
        maven 'Maven_3.9.9'
    }

    environment {
        JIRA_USERNAME = credentials('JIRA_USERNAME')
        JIRA_TOKEN = credentials('JIRA_API_TOKEN')
        JIRA_API_URL = 'https://mtester139.atlassian.net' ///rest/api/2/issue'
        JIRA_PROJECT_KEY = 'ATE'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/ManalKS5/Automation-Exercise.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
