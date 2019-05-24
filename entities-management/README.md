# Topics Management Application

NOTE: This project would not packaged on it's own, needs to packaged via [parent project pom](../pom.xml)

- Provide a topics (cvi) management application using spring boot, spring security, JWT token by using 
graph db as triple store

- Used [vocabulary](./src/main/resources/cvi-vocabulary.sparql) 
    
- The JAR built by maven can be use to deploy the app based on [profile](./src/main/resources/application.yml) default, dev, and docker 

- Manage terms meaning, component types, topics

## Version 0.4.2
- Refactor topics -> entities 

## Version 0.4.1

- Location module added

## Version 0.4.0

- change of api url from `/api/manager/hardware-types` to `/api/manager/component-types`
- version update vocabulary 0.2.2 -> 0.2.3

## Version 0.3.1

- version update vocabulary 0.2.1 -> 0.2.2

## Version 0.3.0

- Manager topics

## Version 0.2.0

- Manage hardware types

### Endpoints

Refer below for [filter, url role access rules](#security)

- `/api/manager/component-types`
    - `POST`: create new component type
        - Request Body: [Component type create dto](#component-type-create-dto)
        - Content type: JSON
        - Response status code: 201 
        - Role: MANAGER
        - Response Body: 
        `message` key has `Component Type created with search id: <SEARCH_ID>` , `SEARCH_ID` for hardware type
    - `GET`: get all component types
        - Content type: JSON
        - Response status code: 200
        - Role: MANAGER 
        - - Response Body: [Results Set](#results-set) collection of elements 
        [component-type-item-details](#component-type-item-details)

### Component type create dto

- keys required are
    - `label`: label for hardware type, represent RDFS label in vocabulary, also used to generate search id
    - `comment`: description of hardware type, represent RDFS comment in vocabulary
    - `hardware component`: either `Sensor` or `Actuator` (sub-classes of `hardware` in CVI vocabulary)  
    - `category`: value would be sub-classes of `Sensor` or `Actuator` in CVI vocabulary, 
    represents the category hardware type belongs to
   
- `hardware type` created would be subclassed under `category` value and `hardware-type` class from CVI Vocabulary


### Component type item details

- keys present are
    - `category`: value would be sub-classes of `Sensor` or `Actuator` in CVI vocabulary, 
    represents the category hardware type belongs to
    - `hardware component`: either `Sensor` or `Actuator` (sub-classes of `hardware` in CVI vocabulary) 
    - `label`: label for hardware type, represent RDFS label in vocabulary, also used to generate search id
    - `comment`: description of hardware type, represent RDFS comment in vocabulary
    - `searchId`: represents `hasSearchId` in vocabulary CVI, search id for hardware type


## Version 0.1.0

- Manage terms meaning

### Endpoints

Refer below for [filter, url role access rules](#security)

- `/api/meaning/terms`
    - `POST`: search for meaning of terms
        - Request Query Parameter: `search_query` string value
        - Content type: JSON
        - Response status Code: 201 
        - Role: AUTHENTICATED
        - Response Body: [Terms Meaning Results Set](#terms-meaning-results-set)
        
        

### Terms Meaning Results Set

- Results set with `exactResults` and `relatedResults` keys, where each is collection of 
[Search item details](#search-item-details) 

- `exactResults` contains exact search query match
- `relatedResults` contains broken terms search query match

For example if we search `parking space sensor` then `exactResults` would look for `parking-space-sensor` in vocabulary
specifically class in vocabulary and `relatedResults` would be  `parking`, `space`, `sensor` class search in vocabulary

example empty terms meaning results set
```
{
    "exactResults": [],
    "relatedResults": []
}
```

example search query `parking`

```
{
    "exactResults": [
        {
            "searchId": "parking",
            "label": "...".
            "comment": "..."
        }
    ],
    "relatedResults": []
}
```

### Search Item Details

search item details has keys
    - `searchId`: represents `hasSearchId` in vocabulary CVI
    - `label`: represents `label` in vocabulary RDFS
    - `comment`: represents `comment` in vocabulary RDFS
    
example search items details for `parking` term
```
{
    "searchId": "parking",
    "label": "...".
    "comment": "..."
}
```


### Results Set

- Results Set type has a field or key in JSON `results`
- array results based on service

example empty result set
```
{
    "results": []
}

```


### Security

- Filters used are
    - `JwtHttpBearerFilter` replaces spring `BasicAuthenticationFilter` filter 
        - Filter that validates token for authentication
        - on successful authentication of [jwt token](#jwt-token) and authorisation allows to access of resource url 
        - on unsuccessful authentication or unauthorised access `401` is returned

- Uses a role hierarchy for authorisation
```
ROLE_ADMIN > ROLE_MANAGER
ROLE_MANAGER > ROLE_CONSUMER
ROLE_CONSUMER > ROLE_AUTHENTICATED
```

### JWT Token

- In header:
    - `alg`: algorithm use `HMAC_SHA256`
    - `typ`: type of token `JWT (JSON WEB TOKEN)`
    
- In payload
    - `sub`: user id as subject
    - `role`: user role
    - `iat`: issued at token time since epoch
    - `exp`: expiration time token since epoch (10000 minutes - randomly we have set, no specific reason)
    - `username`: username of the user
    
   