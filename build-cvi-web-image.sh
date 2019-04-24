#!/usr/bin/env bash

# command build default latest image
# bash build-cvi-web-image.sh

cd ./cvi-web/

docker image build \
    --file Cvi-Web.Dockerfile \
    --tag cvi-web:latest \
    .

cd -