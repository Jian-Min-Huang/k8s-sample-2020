# k8s-sample-2020
> This repository demostrate an architecture for porting ☁️Spring Cloud Netflix solution to 🛳Kubernetes. To achieve this goal, we need to find all the important feature in ☁️Spring Cloud Netflix solution and compare the alternatives. If you are also do same thing I do, I hope this repository can help you. If you have any questions, feel free to contact me 🙂

### frameworks or tools
|feature|Spring|Kubernetes|
|---|---|---|
|Service Discovery|Eureka|Kubernetes DNS|
|Gateway|Zuul & Cloud Gateway|Kubernetes Ingress|
|Traffic|Eureka & Feign & Ribbon|Istio|
|Circuit Break, Rate Limit, Timeout, ...|Hystrix|Istio|
|Monitor|Boot Admin & Actuator|Grafana & Prometheus|
|Trace|Cloud Sleuth & Zipkin|Jaeger|
|Config|Cloud Config|Kubernetes Configmap|
* These two are not fully replace relation ! For example, even you use Grafana & Prometheus you still need Actuator to export the metrics in application.

# k3d, helm, jib, spring jaeger kiali grafana prometheus

### jib
```
echo DOCKER_HUB_USERNAME=$DOCKER_HUB_USERNAME >> ~/.zshrc
echo DOCKER_HUB_PASSWORD=$DOCKER_HUB_PASSWORD >> ~/.zshrc

mvn compile jib:build -Djib.to.auth.username=$DOCKER_HUB_USERNAME -Djib.to.auth.password=$DOCKER_HUB_PASSWORD
```

### helm
```
helm dep up
```

### Monitor
|item|frameworks or tools|
|---|---|
|http 200|Prometheus stack & liveness and readiness probe|
|application log|ELK stack|
|application metrics|Prometheus stack|
|k8s cluster metrics|Prometheus stack|
|services (redis, queue)|Prometheus stack|
|business health||
|business index||
|3rd party service||
|database|Prometheus stack|

### references
* https://github.com/GoogleContainerTools/skaffold
* https://github.com/GoogleContainerTools/jib
