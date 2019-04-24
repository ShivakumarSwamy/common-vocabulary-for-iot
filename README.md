# A Common Vocabulary for IoT

## Application requirements

- Bash Terminal
- Docker
- Git (Optional)

## Setup Steps

1. Clone the project using `git clone <PROJECT_LINK>.git` or download the project and unzip it

1. [Download RDF store image graph db free](#download-rdftriple-store-graphdb-free) 

1. [Build all service images](#build-all-service-image)
  
1. [Run all services](#run-all-services)
                         
1. At [localhost](http://localhost) web app is available

## Download RDF/Triple Store GraphDB Free

1. Register [here](https://www.ontotext.com/products/graphdb/) and follow the steps guided after registration

1. Download the GraphDB free distribution zip file  

1. Place the downloaded zip file in the project root directory

NOTE: Image based on [Ontotext-AD Dockerfile][Ontotext-AD Dockerfile]

## Build all service image

1. (Optional) Navigate to project root directory

1. Build all the images using the [script](./build-all-images.sh)  
    `bash build-all-images.sh` 
     
## Run all services

- In [docker-compose.yml](./docker-compose.yml) file 
    - `users` triple store container `7200` -> `7205`
    - `cvi` triple store container `7200` -> `7210`
    - `app.users` app container `8080` -> `8005`
    - `app.topics` app container `8080` -> `8010`
    - `web.cvi` app container `80` -> `80`
            
- After building all images run, containers using [docker-compose.yml](./docker-compose.yml) file  
`docker-compose up` or `docker-compose -d up` (detached)


[Ontotext-AD Dockerfile]: https://github.com/Ontotext-AD/graphdb-docker/blob/master/free-edition/Dockerfile "Ontotext-AD Dockerfile"