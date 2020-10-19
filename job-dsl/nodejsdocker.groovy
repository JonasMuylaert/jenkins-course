job('NodeJS docker example') {
    scm {
        git('git://github.com/JonasMuylaert/webshop') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('JonasMuylaert')
            node / gitConfigEmail('jonas.muylaert@live.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('jonasmylaert/webshop')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
