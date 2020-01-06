#!/bin/bash

k3d create --server-arg --no-deploy --server-arg traefik

sleep 10s

export KUBECONFIG="$(k3d get-kubeconfig --name='k3s-default')"
helm init
kubectl create serviceaccount --namespace kube-system tiller
kubectl create clusterrolebinding tiller-cluster-rule --clusterrole=cluster-admin --serviceaccount=kube-system:tiller
kubectl patch deploy --namespace kube-system tiller-deploy -p '{"spec":{"template":{"spec":{"serviceAccount":"tiller"}}}}'

echo "sleep 30s, please wait ... "
sleep 30s

istioctl manifest apply --set profile=demo
kubectl label namespace default istio-injection=enabled
