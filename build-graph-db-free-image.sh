#!/usr/bin/env bash

# command build default version image
# bash build-graph-db-free-image.sh

# command build custom version image, pass version as argument to script file, example 8.1.0
# bash build-graph-db-free-image.sh 8.1.0

# final image graph-db-free:<version>

# graph db build argument version, default 8.9.0
version=${1:-8.9.0}

docker image build \
    --file GraphDB-Free.Dockerfile \
    --build-arg version=${version} \
    --tag graph-db-free:${version} \
    --tag graph-db-free:latest \
    .