pipeline
{
    agent any

    tools{
    	maven 'maven'
        }

    environment{

        BUILD_NUMBER = "${BUILD_NUMBER}"

    }


    stages
    {
        stage('Build')
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }



        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }




      stage('Run Docker Image with Regression Tests') {
    steps {
        script {
            def suiteXmlFilePath = 'src/test/resource/testrunners/testng_regression.xml'
            def dockerCommand = """
                docker run --name vaibhavtestapi${BUILD_NUMBER} \
                -v "${WORKSPACE}/reports:/VaibhavGaushetwar/reports" \
                vaibhavgaushetwar/apifwwithdocker:1.0 \
                /bin/bash -c "mvn test -Dsurefire.suiteXmlFiles=${suiteXmlFilePath}"
            """

            def exitCode = bat(script: dockerCommand, returnStatus: true)

            if (exitCode != 0) {
                currentBuild.result = 'FAILURE'
            }
            bat "docker start vaibhavtestapi${BUILD_NUMBER}"
            bat "docker cp vaibhavtestapi${BUILD_NUMBER}:/VaibhavGaushetwar/reports/TestExecutionReport.html ${WORKSPACE}/reports"
            bat "docker cp vaibhavtestapi${BUILD_NUMBER}:/VaibhavGaushetwar/allure-results ${WORKSPACE}/allure-results"
            bat "docker rm -f vaibhavtestapi${BUILD_NUMBER}"
        }
    }
}




		stage('Publish Regression Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: false,
                                  reportDir: 'reports',
                                  reportFiles: 'TestExecutionReport.html',
                                  reportName: 'API HTML Regression Extent Report',
                                  reportTitles: ''])
            }
        }


        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }




    }
}