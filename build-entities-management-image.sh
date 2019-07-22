#!/usr/bin/env bash

# command build default version image
# bash build-entities-management-image.sh

# command build custom version image, pass version as argument to script file, example 8.1.0
# bash build-entities-management-image.sh 8.1.0

# final image entities-management:<version> and entities-management:latest

version=${1:-0.4.2}

docker image build \
    --file Entities-Management.Dockerfile \
    --build-arg version=${version} \
    --tag entities-management:${version} \
    --tag entities-management:latest \
    .