# A Common Vocabulary for IoT

## Application requirements

- Bash Terminal
- Git
- Maven
- Docker
- Npm

## Setup Steps

1. Clone the project using `git clone <PROJECT_LINK>.git`

1. [Download RDF store image graph db free](#download-rdftriple-store-graphdb-free) 

1. [Package application to jars](#package-application-to-jars)

1. [Build all service images](#build-all-service-image)
    - `graph-db-free:<version>` image exposes `7200` port
    - `users-management:<version>` image exposes `8080` port
    - `topic-management:<version>` image exposes `8080` port

1. [Run all services](#run-all-services)
                         
1. [Run Web app](#run-web-app)

## Download RDF/Triple Store GraphDB Free

1. Register [here](https://www.ontotext.com/products/graphdb/) and follow the steps guided after registration

1. Download the GraphDB free distribution zip file  

1. Place the downloaded zip file in the [project root directory](./)

1. (Optional)Build the image using the [script](./build-graph-db-free-image.sh)  
    `bash build-graph-db-free-image.sh`

NOTE: 

- Image based on [Ontotext-AD Dockerfile][Ontotext-AD Dockerfile]

- The image format is `graph-db-free:<VERSION>`

- GraphDB free default version is set to `8.9.0`, so the image built would be `graph-db-free:8.9.0`

- GraphDB free image with custom version could be build, 
by passing version as argument to [script](build-graph-db-free-image.sh)  
`bash build-graph-db-free-image.sh <VERSION>`


## Package application to jars

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

- In [docker-compose.yml](./docker-compose.yml) file 
    - port mapping (if port needs to be changed then ports 
    should be changed in [proxy config file for docker](./cvi-web/proxy-config-docker.json))
        - `users` triple store container `7200` -> `7205`
        - `cvi` triple store container `7200` -> `7210`
        - `users-management` app container `8080` -> `8005`
        - `topics-management` app container `8080` -> `8010`
            
- After building all images run, containers using [docker-compose.yml](./docker-compose.yml) file  
`docker-compose up` or `docker-compose -d up` (detached)



## Run Web app

1. [cvi-web](./cvi-web) contains a angular application

1. Install all dependencies using `npm` and [angular.json](./cvi-web/angular.json)

1. Run the angular server using a [proxy config file](./cvi-web/proxy-config-docker.json) to proxy to spring application server  
`ng serve --proxy-config proxy-config-docker.json` 


[Ontotext-AD Dockerfile]: https://github.com/Ontotext-AD/graphdb-docker/blob/master/free-edition/Dockerfile "Ontotext-AD Dockerfile"