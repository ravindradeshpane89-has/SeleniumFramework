pipeline {
    agent any

    parameters {
        // Dropdown parameter allowing the user to select the Maven Profile
        choice(
            name: 'MAVEN_PROFILE', 
            choices: ['Regression', 'ErrorValidation','DataDriven'], 
            description: 'Select the Maven profile / test suite to execute'
        )

         choice(
            name: 'Browser', 
            choices: ['chrome', 'edge','firefox','safari'], 
            description: 'Select the Maven profile / test suite to execute'
        )
    }

    tools {
        // Ensure this matches the Global Tool Configuration name in Jenkins
        maven 'Maven' 
        jdk 'Java25'
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Pulls code from your repository
                checkout scm
            }
        }

        stage('Execute TestNG Tests') {
            steps {
                script {
                    echo "Executing TestNG tests using profile: ${params.MAVEN_PROFILE}"
                    
                    // Runs the selected profile using the -P flag
                    // Use 'bat' instead of 'sh' if your Jenkins agent runs on Windows
                    bat "mvn clean test -P${params.MAVEN_PROFILE} -Dbrowser=${params.Browser}"
                }
            }
        }
    }

    post {
        always {
            // Archive and publish Cucumber HTML reports
            // Requires the HTML Publisher Plugin or Cucumber Reports Plugin
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'reports',
                reportFiles: 'index.html',
                reportName: 'Extent Report'
            ])
        }
        success {
            echo 'Tests completed successfully!'
        }
        failure {
            echo 'Tests failed. Check the Cucumber reports for details.'
        }
    }
}