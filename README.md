# A Common Vocabulary for IoT

## Application requirements

- Bash Terminal
- Maven
- Docker
- Npm

## Build RDF/Triple Store GraphDB Free Image

1. Register [here](https://www.ontotext.com/products/graphdb/) and follow the steps guided after registration

1. Download the GraphDB free distribution zip file  

1. Place the downloaded zip file in the [project root directory](./)

1. Build the image using the [script](./build-graph-db-free-image.sh)  
    `bash build-graph-db-free-image.sh`

NOTE: 

- Image based on [Ontotext-AD Dockerfile][Ontotext-AD Dockerfile]

- The image format is `graph-db-free:<VERSION>`

- GraphDB free default version is set to `8.9.0`, so the image built would be `graph-db-free:8.9.0`

- GraphDB free image with custom version could be build, 
by passing version as argument to [script](build-graph-db-free-image.sh)  
`bash build-graph-db-free-image.sh <VERSION>`


## Package jar

1. Navigate to [project root directory](./)

1. Run `mvn package` to build all jars

## Build users management image

1. Navigate to [project root directory](./)

1. Build the image using the [script](./build-users-management-image.sh)  
    `bash build-users-management-image.sh` 

## Build topics management image

1. Navigate to [project root directory](./)

1. Build the image using the [script](./build-topics-management-image.sh)  
    `bash build-topics-management-image.sh` 
    

## Build all service image

1. Navigate to [project root directory](./)

1. Build the image using the [script](./build-all-images.sh)  
    `bash build-all-images.sh` 
     
## Run all services

- After building all images run, 
`docker-compose up`


## Run Web app

1. [cvi-web](./cvi-web) contains a angular application

1. Install all dependencies using `npm` and [angular.json](./cvi-web/angular.json)

1. Run the angular server using a [proxy config file](./cvi-web/proxy-config.json) to proxy to spring application server
`ng serve --proxy-config proxy-config.json` 


[Ontotext-AD Dockerfile]: https://github.com/Ontotext-AD/graphdb-docker/blob/master/free-edition/Dockerfile "Ontotext-AD Dockerfile"