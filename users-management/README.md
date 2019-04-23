# User Management Application

NOTE: This project would not packaged on it's own, needs to packaged via [parent project pom](../pom.xml)

- Provide a user management application using spring boot, spring security, JWT token by using 
graph db as triple store

- Has three roles based on [vocabulary](./src/main/resources/user-vocabulary.sparql) 
    - Admin
    - Manager
    - Consumer
    
- One Admin account is loaded into vocabulary 
    - username: `iot-admin`
    - password: `vocabulary-admin`
    
- Only Consumer and Manager role account can be created

- The JAR built by maven can be use to deploy the app based on [profile](./src/main/resources/application.yml) default, dev, and docker 

## Version 0.1.2

- fix vocabulary version 0.2.1 -> 0.2.3

## Version 0.1.1

- add username to JWT token

## Version 0.1.0

### Endpoints

Refer below for [filter, url role access rules](#security)

- `/api/users`
    - `POST`: create a consumer or manager user
        - Request Body: username, password, role
        - Content type: JSON
        - Response status Code: 201 
        - Role: any anonymous can create account
        
    - `GET`: Based on JWT token, get the user details
        - Response Body: [Results Set](#results-set) with `results` key having a single [user details](#user-details) in a collection
        - Content type: JSON
        - Response status code: 200
        - Role: AUTHENTICATED
        
- `/api/admin/users`:
    - `GET`: get all users
        - [Results Set](#results-set) with `results` key having a collection of all [user details](#user-details)
        - Content type: JSON
        - Response status code: 200
        - Role: ADMIN

- `/api/login`: uses filter `LoginAuthenticationJwtTokenGeneratorFilter`
    - `POST`: login user
        - Response Header Bearer Token: `Bearer <JWT_TOKEN>`
        - Response status code: 204
        
### User Details

- Keys of user details object
    - `id`: id of user
    - `username`: username of user
    - `role`: role of user
    
     
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
    - `LoginAuthenticationJwtTokenGeneratorFilter` replaces spring `UsernamePasswordAuthenticationFilter` filter
        - validates user based on username and password
        - on successful authentication returns a [JWT Token](#jwt-token) in header
        - on unsuccessful authentication `401` is returned   
    - `JwtHttpBearerFilter` replaces spring `BasicAuthenticationFilter` filter 
        - Filter that validates token for authentication
        - on successful authentication of token and authorisation allows to access of resource url 
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
    
   