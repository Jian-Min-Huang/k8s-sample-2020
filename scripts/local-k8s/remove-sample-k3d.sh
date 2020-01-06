#!/bin/bash

export KUBECONFIG="$(k3d get-kubeconfig --name='k3s-default')"

helm delete --purge sample
k3d delete
