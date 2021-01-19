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
        stage("startEnvironment"){
            steps{
                echo "starting server"
                dir("/home/student/JavaTools/db-derby-10.15.1.3-bin/bin"){
                    sh "./startNetworkServer &"
                }
                dir("/home/student/JavaTools/payara5.2020.5/bin"){
                    sh "./asadmin start-domain domain1"
                }
            }
        }
        stage("db"){
            steps{
            echo "db-init"
                dir("/home/student/JavaTools/db-derby-10.15.1.3-bin/bin"){
                    sh "echo \"run 'init.sql';\" | ./ij"
                    sh "echo \"run 'fill.sql';\" | ./ij"
                }
            }
        }
        
        stage("build"){
            steps{
                echo "Building"
                sh "mvn package -DskipTests"
            }
            post{
                success{
                    sh "mvn cargo:redeploy -Ppayara-remote"
                }
            }
        }

        stage("Integration tests"){
            steps{
                echo "Integration tests"
                sh "mvn test -Psurefire"
            }
        }
    }
}
