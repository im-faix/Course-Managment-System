pipeline 
{
  agent any

  environment 
  {
    IMAGE_NAME = "course-app"
    CONTAINER_NAME = "course-app-container"
  }

  stages 
  {
      stage('clean-workspace')
      {
          steps
          {
              cleanWs()
          }
      }
      stage('Clone Repo') 
    {
      steps 
      {
        sh 'git clone https://github.com/im-faix/Course-Managment-System.git .'
      }
    }
    stage('Build JAR') 
    {
      steps 
      {
        sh 'mvn clean package -DskipTests'
      }
    }
    stage('Build Image'){
        steps{
            sh 'docker build -t $IMAGE_NAME:$BUILD_ID .'
        }
    }
    

    stage('Run Docker Container') {
  steps {
    sh 'docker-compose up -d'
  }
}

  }

  post {
    always {
      sh 'docker ps -a'
    }
  }
}
