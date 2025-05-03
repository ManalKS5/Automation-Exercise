pipeline {
    agent any

    tools {
        jdk 'JDK_21'
        maven 'Maven_3.9.9'
        allure 'Allure_2.34.0'
    }

    environment {
        JIRA_USERNAME    = credentials('JIRA_USERNAME')
        JIRA_TOKEN       = credentials('JIRA_API_TOKEN')
        JIRA_API_URL     = 'https://mtester139.atlassian.net'  ///rest/api/2/issue'
        JIRA_PROJECT_KEY = 'ATE'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/ManalKS5/Automation-Exercise.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Basic Tests') {
            steps {
                bat 'mvn test -DsuiteXmlFile=testng-basic.xml'
            }
        }

        stage('Run Data-Driven Tests') {
            steps {
                bat 'mvn test -DsuiteXmlFile=testng-data.xml'
            }
        }

        stage('Run FailureTests') {
             steps {
                 bat 'mvn test -DsuiteXmlFile=testing.xml -Dtest=FailureTests'
              }
         }

        stage('Generate Allure Report') {
            steps {
                bat '"C:\\tmp\\allure-2.34.0\\bin\\allure.bat" generate allure-results --clean -o allure-report'
            }
        }

    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            allure([
                includeProperties: false,
                jdk: '',
                results: [[path: 'allure-results']]
            ])
        }
    }
}
