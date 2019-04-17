# Initial Load Vocabulary

NOTE: This project would not packaged on it's own, needs to packaged via [parent project pom](../pom.xml)

- Helps to do initial load of the vocabulary by creating a repository, load the vocabulary from a file

- Force initial load also available after repository is created

- The project which uses this module as dependency has to implement interface 
`DataSourceEndpoints`(triple store endpoints) and `DataSourceProperties` (triple store details)

- As of now the default repository created has `rdfsplus-optimized` as rule set, type `free` 
and sesame type `graphdb:FreeSailRepository`

- This module some helpful spring utils for web

### Version 0.1.0

- interfaces:
    - DataSourceEndpoints
    - DataSourceProperties
    
- service:
    - RepositoryService
    - RepositoryServiceException
    
- dto
    - RepositoryConfigDetails (GraphDB specific)     
    
- utils
    - MediaTypeConstants - text/boolean, application/sparql-results+json 
    - HttpEntityFactory - sparql ask, update, and query entities
    
    
#### DataSourceEndpoints

- Endpoints required
    - Repository in case of `rdf4j` or dataset
        - create 
        - delete
        - read all repositories
        
    - Statements (perform operations on specific repository)
        - graph update
        - query

#### DataSourceProperties

- Properties required
    - scheme
    - host
    - port
    - repository id 
    - initial load file location
