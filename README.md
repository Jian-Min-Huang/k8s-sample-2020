# k8s-sample-2020
> This repository demostrate an architecture for porting ☁️Spring Cloud Netflix solution to 🛳Kubernetes. To achieve this goal, we need to find all the important feature in ☁️Spring Cloud Netflix solution and compare the alternatives. If you are also do same thing I do, I hope this repository can help you. If you have any questions, feel free to contact me 🙂

# compare
||Spring|Kubernetes|
|---|---|---|
|Service Discovery|Eureka|Kubernetes DNS|
|Gateway|Zuul & Cloud Gateway|Kubernetes Ingress|
|Traffic|Eureka & Feign & Ribbon|Istio|
|Circuit Break, Rate Limit, Retry, Timeout|Hystrix|Istio|
|(Fault injection & fall back)|||
|Monitor|Boot Admin & Actuator|Grafana & Prometheus|
|Trace|Sleuth & Zipkin|Jaeger|
|Config|Cloud Config|Kubernetes Configmap|

# k3d, helm, jib, spring jaeger kiali grafana prometheus

### jib
```
echo DOCKER_HUB_USERNAME=$DOCKER_HUB_USERNAME >> ~/.zshrc
echo DOCKER_HUB_PASSWORD=$DOCKER_HUB_PASSWORD >> ~/.zshrc

mvn compile jib:build -Djib.to.auth.username=$DOCKER_HUB_USERNAME -Djib.to.auth.password=$DOCKER_HUB_PASSWORD
```

```
helm dep up
```

### references
* https://github.com/GoogleContainerTools/skaffold
* https://github.com/GoogleContainerTools/jib
