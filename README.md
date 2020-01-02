# k8s-sample-2020
> This repository demostrate an architecture for porting ☁️Spring Cloud Netflix solution to 🛳Kubernetes. To achieve this goal, we need to find all the important feature in ☁️Spring Cloud Netflix solution and compare the alternatives. If you are also do same thing I do, I hope this repository can help you. If you have any questions, feel free to contact me 🙂.

## Frameworks or Tools 🛠
|feature|Spring|Kubernetes|
|---|---|---|
|Service Discovery|Eureka|Internal DNS|
|Gateway|Zuul & Cloud Gateway|Ingress|
|Traffic|Eureka & Feign & Ribbon|Istio|
|Circuit Break, Rate Limit, Retry, Timeout, Fast Fall|Hystrix|Istio|
|Monitor|Boot Admin & Actuator|Grafana & Prometheus|
|Trace|Cloud Sleuth & Zipkin|jaeger|
|Config|Cloud Config|Configmap|
* These two are not fully replaced relation! For example, even you use Grafana & Prometheus you still need Actuator to export the metrics in application.
* Istio doesn't have downgrade fallback feature until 1.4.

## Monitor 📺
|item|frameworks or tools|
|---|---|
|application log|ELK stack|
|business index|Prometheus stack via Elasticsearch|
|http 200|Prometheus stack & liveness and readiness probe|
|application metrics|Prometheus stack, jaeger|
|services (redis, queue)|Prometheus stack|
|3rd party service health|Prometheus stack|
|k8s cluster metrics|Prometheus stack, kiali|
|database|Prometheus stack|
|business health|postman newman or Pytest Allure|

## Prerequisite (assume u r a macOS and Z shell coder 👻)
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
umm ..., I need to teach you this ? 🤣
```

## k3d https://github.com/rancher/k3d
install and create default cluster
```
brew install k3d
k3d create --server-arg --no-deploy --server-arg traefik
export KUBECONFIG="$(k3d get-kubeconfig --name='k3s-default')"
kubectl cluster-info
```

## helm 2 https://v2.helm.sh/
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

## Istio https://istio.io/
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

## helm install
```
helm install --name sample-redis --values ../helm/values.yaml stable/redis
helm install --name sample-rabbitmq --values ../helm/values.yaml stable/rabbitmq
helm install --name sample-mysql --values ../helm/values.yaml stable/mysql
helm install --name sample ./helm
kubectl port-forward svc/istio-ingressgateway 10080:80 -n istio-system
```

## Grafana https://grafana.com/
## jaeger https://www.jaegertracing.io/
## kiali https://kiali.io/
enable Grafana, jaeger and kiali with istioctl in default cluster
```
istioctl manifest apply \
    --set values.kiali.enabled=true \
    --set values.grafana.enabled=true \
    --set values.tracing.enabled=true \
    --set "values.kiali.dashboard.jaegerURL=http://jaeger-query:63399" \
    --set "values.kiali.dashboard.grafanaURL=http://grafana:3000"
kubectl -n istio-system get svc prometheus
kubectl -n istio-system get svc grafana
kubectl -n istio-system get svc kiali
kubectl -n istio-system port-forward $(kubectl -n istio-system get pod -l app=grafana -o jsonpath='{.items[0].metadata.name}') 3000:3000 &
istioctl dashboard jaeger
istioctl dashboard kiali
```
![grafana](https://github.com/Jian-Min-Huang/k8s-sample-2020/blob/master/doc/images/grafana.png?raw=true)
![jaeger](https://github.com/Jian-Min-Huang/k8s-sample-2020/blob/master/doc/images/jaeger.png?raw=true)
![kiali](https://github.com/Jian-Min-Huang/k8s-sample-2020/blob/master/doc/images/kiali.png?raw=true)

## jib https://github.com/GoogleContainerTools/jib
```
echo DOCKER_HUB_USERNAME=$DOCKER_HUB_USERNAME >> ~/.zshrc
echo DOCKER_HUB_PASSWORD=$DOCKER_HUB_PASSWORD >> ~/.zshrc

./mvnw compile jib:build -Djib.to.auth.username=$DOCKER_HUB_USERNAME -Djib.to.auth.password=$DOCKER_HUB_PASSWORD
```
* [Why jib ?](https://github.com/Jian-Min-Huang/tech-note/issues/32)

## cleanup
```
helm delete --purge sample
istioctl manifest generate --set profile=demo | kubectl delete -f -
k3d delete
```

## references
* https://github.com/GoogleContainerTools/skaffold
