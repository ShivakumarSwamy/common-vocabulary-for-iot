# Topics Management Application

NOTE: This project would not packaged on it's own, needs to packaged via [parent project pom](../pom.xml)

- Provide a topics (cvi) management application using spring boot, spring security, JWT token by using 
graph db as triple store

- Used [vocabulary](./src/main/resources/cvi-vocabulary.sparql) 
    
- The JAR built by maven can be use to deploy the app based on [profile](./src/main/resources/application.yml) default, dev, and docker 

## Version 0.1.0

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
    - `searchId`: represents `hasSerachId` in vocabulary CVI
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

example empty result set
```
{
    "results": []
}

```

example single user details
```
{
    "results": [
        {
            "id": "foo",
            "username": "foo",
            "role": "foo"
        }
    ]
}    
```

### Security

- Filters used are
    - `JwtHttpBearerFilter` replaces spring `BasicAuthenticationFilter` filter 
        - Filter that validates token for authentication
        - on successful authentication of [jwt token](#jwt-token) and authorisation allows to access of resource url 
        - on unsuccessful authentication `401` is returned

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
    
   