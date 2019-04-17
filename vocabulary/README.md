# Vocabulary module

NOTE: 
- This project would not packaged on it's own, needs to packaged via [parent project pom](../pom.xml)
- Only needed properties, class for project is present in the module

### Version 0.2.1


- CVI iri fix: `http://www.cvi.com/vocabulary#`

- [RDFS](http://www.w3.org/2000/01/rdf-schema# "RDFS")    
    - local names:
        - comment
        
### Version 0.2.0

- More convenience methods for `ProloguesFactory` 

- CVI
    - prefix label: `cvi`
    - iri: `http://www.cvi.com/vocabulary#`
    
    - local names
        - hasSearchId
        
        - entity
        - hasId
        - hasOwner
        
        - topic
        - hasPath
        - hasMiddlewareEndpoint
        - hasDataType
        - hasTopicType
        - hasProtocol
        
        - message
        - hasMessageFormat
        - hasMetaModel
        - hasMetaModelType
        
        - hardware
        - hasHardwareType
        - hasUnit
        
        - hardware-type 
          
### Version 0.1.1

- USER
    - prefix label: `usr`
    - iri: `http://www.example.com/vocabulary/user#`
    
    - local names:
        - hasRole
        

### Version 0.1.0

- [RDF](http://www.w3.org/1999/02/22-rdf-syntax-ns# "RDF")
    - prefix label: `rdf`
    - iri: `http://www.w3.org/1999/02/22-rdf-syntax-ns#`
    
    - local names:
        - type
        

- [RDFS](http://www.w3.org/2000/01/rdf-schema# "RDFS")
    - prefix label: `rdfs`
    - iri: `http://www.w3.org/2000/01/rdf-schema#`
    
    - local names:
        - Class
        - label

- [OWL](http://www.w3.org/2002/07/owl# "OWL")
    - prefix label: `owl`
    - iri: `http://www.w3.org/2002/07/owl#`
    
    - local names:
        - DatatypeProperty
        - ObjectProperty
        
- USER
    - prefix label: `usr`
    - iri: `http://www.example.com/vocabulary/user#`
    
    - local names:
        - user
        - hasId
        - hasUsername
        - hasPassword