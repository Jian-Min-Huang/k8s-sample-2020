k3d create --server-arg --no-deploy --server-arg traefik
sleep 10s
export KUBECONFIG="$(k3d get-kubeconfig --name='k3s-default')"
helm init
kubectl create serviceaccount --namespace kube-system tiller
kubectl create clusterrolebinding tiller-cluster-rule --clusterrole=cluster-admin --serviceaccount=kube-system:tiller
kubectl patch deploy --namespace kube-system tiller-deploy -p '{"spec":{"template":{"spec":{"serviceAccount":"tiller"}}}}'
istioctl manifest apply --set profile=demo
kubectl label namespace default istio-injection=enabled
k3d import-images local/sample-pt-gw:1
sleep 3s
k3d import-images local/sample-lg-gw:1
sleep 3s
k3d import-images local/sample-task:1
sleep 3s
k3d import-images local/sample-member:1
sleep 3s
k3d import-images local/sample-item:1
