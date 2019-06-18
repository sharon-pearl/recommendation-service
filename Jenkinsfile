pipeline {
  agent {
        label 'slave'
    }
    stages {
          stage ('Checkout'){
          steps {
            checkout([
                $class: 'GitSCM', 
                branches: [[name: 'origin/master']], 
                extensions: [[
    			    $class: 'PathRestriction', 
    			    excludedRegions: 'recommendation-service/tree/master/src/main/resources/.*yml'
    		    ]],
            ])
          }
      }
    }

}
