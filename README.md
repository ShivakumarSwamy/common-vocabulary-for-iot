# A Common Vocabulary for the Internet of Things

## Table of Contents

* [Abstract](#abstract)
* [Application requirements](#application-requirements)
* [Setup](#setup)
    * [Download RDF/Triple Store GraphDB Free Version](#download-rdftriple-store-graphdb-free-version)
    * [Build all services image](#build-all-services-image)
    * [Run all services](#run-all-services)

### Abstract
> The vision of Internet of Things (IoT) is to enable a global network of devices, intelligent enough to interact with one another, over the Internet. These devices are embedded with a myriad of entities (sensors and actuators), which can be remotely monitored and controlled using an IoT application. To receive data from a sensor or send data to the actuator and to further provide the data to IoT applications, many IoT platforms have been developed in the past using different protocols (MQTT, HTTP). Examples for such platforms are Mosquitto, OpenMTC, and FIWARE. Application developers who build these IoT applications, priorly are aware of the entities they host, how to access them and the structure of the message exchanged. As IoT environments are huge and dynamic, the addition of entities or change in the properties of the message or data, happen regularly. This leads to changes in the IoT application frequently, which increases the cost of the application. Besides, the application developer is coupled with only the knowledge of entities they host in that IoT environment. Moreover, if there are any other entities in the same environment, with better coverage or with any other additional information, the IoT application will not be able to make use of it as it will not be aware of how to access it. In order to adapt IoT applications to the dynamic nature of IoT, we need to define how the data can be retrieved from these IoT platforms, i.e., describe the properties of the entities, the structure of the data and its location. Then, IoT applications can use these properties to search for the required entities, in large IoT environments, and later access them without knowing in advance. In this thesis, we introduce a common vocabulary for the Internet of Things, based on semantic technologies, that enables us to describe the properties of entities. Furthermore, we show how to describe the entities using the terms in the vocabulary and how to search for the entities based on common terms. Our approach is demonstrated with an example scenario from the domain of smart cities.

### Application requirements

- Bash Terminal
- Docker
- Git (Optional)

### Setup

1. Clone the project using `git clone <PROJECT_LINK>.git` or download the project and unzip it

1. [Download RDF/Triple Store GraphDB Free Version](#download-rdftriple-store-graphdb-free-version) 

1. [Build all service images](#build-all-services-image)
  
1. [Run all services](#run-all-services)
                         
1. At [localhost](http://localhost) web app is available 

#### Download RDF/Triple Store GraphDB Free Version

1. Register [here](https://www.ontotext.com/products/graphdb/) and follow the steps guided after registration

1. Download the GraphDB free distribution zip file  

1. Place the downloaded zip file in the project root directory

NOTE: Image based on [Ontotext-AD Dockerfile][Ontotext-AD Dockerfile]

#### Build all services image

1. Navigate to project root directory

1. Build all the images using the [script](./build-all-images.sh)  
    `bash build-all-images.sh` 
     
#### Run all services

- After building all images, spin up containers using [docker-compose.yml](./docker-compose.yml) file  
`docker-compose up` or `docker-compose up -d` (detached)

- [docker-compose.yml](./docker-compose.yml) file has following configuration
    - Configuration - image name: container name: port forwarding
        - `graph-db-free`: `users`: `7200` -> `7205`
        - `graph-db-free`: `cvi`: `7200` -> `7210`
        - `users-management`: `app.users`: `8080` -> `8005`
            - API Documentation: `http://localhost:8005/swagger-ui.html`
        - `entities-management`: `app.entities`: `8080` -> `8010`
            - API Documentation: `http://localhost:8010/swagger-ui.html`
        - `cvi-web`: `web.cvi`: `80` -> `80`
            



[Ontotext-AD Dockerfile]: https://github.com/Ontotext-AD/graphdb-docker/blob/master/free-edition/Dockerfile "Ontotext-AD Dockerfile"