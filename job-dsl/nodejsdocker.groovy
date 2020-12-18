job('NodeJS Docker example') {
    scm {
        git('git://github.com/Nishantchhabra/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('nishantchhabra5@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
   
    steps {
        dockerBuildAndPublish {
            repositoryName('docker-nishant/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
