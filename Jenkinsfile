pipeline {
  agent any
    stages {
          stage ('Checkout'){
          steps {
            checkout([
                $class: 'GitSCM', 
                branches: [[name: 'origin/master']], 
                extensions: [[
    			    $class: 'PathRestriction', 
    			    excludedRegions: './src/main/resources/.*yml'
    		    ]],
            ])
          }
      }
        stage('Get environmental variables') {
          steps {
            script {
               echo "Trying to get info from roche cluster config"
            }
          }
        }
    }

}
