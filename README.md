# A Common Vocabulary for IoT

## Application requirements

- Bash Terminal
- Docker

## Build RDF/Triple Store GraphDB Free Image

1. Register [here](https://www.ontotext.com/products/graphdb/) and follow the steps guided after registration

1. Download the GraphDB free distribution zip file  

1. Place the downloaded zip file in the [project root directory](./)

1. Build the image using the [script](build-graph-db-free-image.sh)  
    `bash build-graph-db-free-image.sh`

NOTE: 

- Image based on [Ontotext-AD Dockerfile][Ontotext-AD Dockerfile]

- The image format is `graph-db-free:<VERSION>`

- GraphDB free default version is set to `8.9.0`, so the image built would be `graph-db-free:8.9.0`

- GraphDB free image with custom version could be build, 
by passing version as argument to [script](build-graph-db-free-image.sh)  
`bash build-graph-db-free-image.sh <VERSION>`

## Run all services

- After building all images run, 
`docker-compose up`


[Ontotext-AD Dockerfile]: https://github.com/Ontotext-AD/graphdb-docker/blob/master/free-edition/Dockerfile "Ontotext-AD Dockerfile"