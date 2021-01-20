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
                sh "cp init.sql /home/student/JavaTools/db-derby-10.15.1.3-bin/bin/init.sql"
                sh "cp /src/main/resources/createDB.sql /home/student/JavaTools/db-derby-10.15.1.3-bin/bin/initDB.sql"
                sh "cp /src/main/resources/init.sql /home/student/JavaTools/db-derby-10.15.1.3-bin/bin/createDB.sql"
                dir("/home/student/JavaTools/db-derby-10.15.1.3-bin/bin"){
                    sh "echo \"run 'init.sql';\" | ./ij"
                }
                sh "rm -rf /home/student/JavaTools/db-derby-10.15.1.3-bin/bin/init.sql"
                sh "rm -rf /home/student/JavaTools/db-derby-10.15.1.3-bin/bin/createDB.sql"
                sh "rm -rf /home/student/JavaTools/db-derby-10.15.1.3-bin/bin/initDB.sql"
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
