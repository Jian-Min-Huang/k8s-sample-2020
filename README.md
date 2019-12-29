# k8s-sample-2020
k8s-sample-2020

```
mvn compile jib:build \
-Djib.to.auth.username=$DOCKER_HUB_USERNAME \
-Djib.to.auth.password=$DOCKER_HUB_PASSWORD
```

```
helm dep up
```

### references
* https://github.com/GoogleContainerTools/skaffold
* https://github.com/GoogleContainerTools/jib
