pipeline {
    agent any
    stages {
        stage("git"){
            steps{
                git "https://github.com/matee98/ISDP_zad3.git"
            }
        }
        stage("clean"){
            steps{
                echo "pre-built"
                sh "mvn clean"
            }
            post {
                success{
                    echo "Succesfully deleted old project"
                }
            }
        }
        
        stage("build"){
            steps{
                echo "Building"
                sh "mvn package"
            }
            post{
                success{
                    sh "mvn cargo:redeploy -Ppayara-remote"
                }
            }
        }
    }
}
