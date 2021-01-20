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
        stage("db"){
            steps{
            echo "db-init"
                sh "cp ./setup.sh /home/student/JavaTools/db-derby-10.15.1.3-bin/bin/setup.sh"
                sh "cp ./src/main/resources/createDB.sql /home/student/JavaTools/db-derby-10.15.1.3-bin/bin/createDB.sql"
                sh "cp ./src/main/resources/initDB.sql /home/student/JavaTools/db-derby-10.15.1.3-bin/bin/initDB.sql"
                dir("/home/student/JavaTools/db-derby-10.15.1.3-bin/bin"){
                    sh "expect ./setup.sh"
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
                //test
            }
        }
    }
}
