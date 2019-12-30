# k8s-sample-2020
> This repository demostrate an architecture for porting ☁️Spring Cloud Netflix solution to 🛳Kubernetes. To achieve this goal, we need to find all the important feature in ☁️Spring Cloud Netflix solution and compare the alternatives. If you are also do same thing I do, I hope this repository can help you. If you have any questions, feel free to contact me 🙂

### Frameworks or Tools
|feature|Spring|Kubernetes|
|---|---|---|
|Service Discovery|Eureka|Kubernetes DNS|
|Gateway|Zuul & Cloud Gateway|Kubernetes Ingress|
|Traffic|Eureka & Feign & Ribbon|Istio|
|Circuit Break, Rate Limit, Timeout, ...|Hystrix|Istio|
|Monitor|Boot Admin & Actuator|Grafana & Prometheus|
|Trace|Cloud Sleuth & Zipkin|jaeger|
|Config|Cloud Config|Kubernetes Configmap|
* These two are not fully replaced relation ! For example, even you use Grafana & Prometheus you still need Actuator to export the metrics in application.

### Monitor
|item|frameworks or tools|
|---|---|
|http 200|Prometheus stack & liveness and readiness probe|
|application log|ELK stack|
|application metrics|Prometheus stack, jaeger|
|k8s cluster metrics|Prometheus stack, kiali|
|services (redis, queue)|Prometheus stack|
|business health|newman or python ?|
|business index|Prometheus stack|
|3rd party service|custom exporter ?|
|database|Prometheus stack|

### Prerequisite
install brew
```
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```
install kubectl
```
brew install kubectl
```
install Java
```
umm, I need to teach you this ? 🤣
```

### k3d https://github.com/rancher/k3d
install and create default cluster
```
brew install k3d
k3d create --server-arg --no-deploy --server-arg traefik
export KUBECONFIG="$(k3d get-kubeconfig --name='k3s-default')"
kubectl cluster-info
```

### helm 2 https://v2.helm.sh/
install
```
brew install helm@2
echo 'export PATH="/usr/local/opt/helm@2/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```
delete it not empty, like a reinstall process
```
kubectl get all --all-namespaces | grep tiller
kubectl delete deployment tiller-deploy -n kube-system
kubectl delete service tiller-deploy -n kube-system
```
should be empty
```
kubectl get all --all-namespaces | grep tiller
```
configure helm on default cluster
```
helm version
helm init
kubectl create serviceaccount --namespace kube-system tiller
kubectl create clusterrolebinding tiller-cluster-rule --clusterrole=cluster-admin --serviceaccount=kube-system:tiller
kubectl patch deploy --namespace kube-system tiller-deploy -p '{"spec":{"template":{"spec":{"serviceAccount":"tiller"}}}}'
```

### Istio https://istio.io/
install
```
cd ~ && curl -L https://istio.io/downloadIstio | sh -
cd istio-1.4.2
echo 'export PATH="'$(pwd)'/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```
configure istio on default cluster
```
istioctl manifest apply --set profile=demo
kubectl get svc,pods -n istio-system
kubectl label namespace default istio-injection=enabled
```

### helm
```
helm install --name sample-mysql --set mysqlRootPassword=1qaz2wsx,mysqlDatabase=test stable/mysql
helm install --name sample .
```

### Grafana https://grafana.com/
enable Grafana with istioctl in default cluster
```
istioctl manifest apply --set values.grafana.enabled=true
kubectl -n istio-system port-forward $(kubectl -n istio-system get pod -l app=grafana -o jsonpath='{.items[0].metadata.name}') 3000:3000
```

### jaeger https://www.jaegertracing.io/
enable jaeger with istioctl in default cluster
```
istioctl manifest apply --set values.tracing.enabled=true
istioctl dashboard jaeger
```

### kiali https://kiali.io/
enable kiali with istioctl in default cluster
```
istioctl manifest apply \
    --set values.kiali.enabled=true \
    --set values.grafana.enabled=true \
    --set values.tracing.enabled=true \
    --set "values.kiali.dashboard.jaegerURL=http://jaeger-query:63399" \
    --set "values.kiali.dashboard.grafanaURL=http://grafana:3000"
istioctl dashboard kiali
```

### jib
```
echo DOCKER_HUB_USERNAME=$DOCKER_HUB_USERNAME >> ~/.zshrc
echo DOCKER_HUB_PASSWORD=$DOCKER_HUB_PASSWORD >> ~/.zshrc

./mvnw compile jib:build -Djib.to.auth.username=$DOCKER_HUB_USERNAME -Djib.to.auth.password=$DOCKER_HUB_PASSWORD
```
* why jib but not Dockerfile

### references
* https://github.com/GoogleContainerTools/skaffold
