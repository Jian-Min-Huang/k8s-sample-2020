#!/bin/bash

helm delete --purge sample
k3d delete
