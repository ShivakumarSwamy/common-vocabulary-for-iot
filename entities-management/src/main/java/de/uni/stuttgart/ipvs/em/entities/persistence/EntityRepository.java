package de.uni.stuttgart.ipvs.em.entities.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.uni.stuttgart.ipvs.em.entities.service.Entity;
import de.uni.stuttgart.ipvs.ilv.HttpEntityFactory;
import de.uni.stuttgart.ipvs.ilv.config.DataSourceEndpoints;
import de.uni.stuttgart.ipvs.results.SelectResults;

import java.util.Collection;
import java.util.Map;

@Repository
public class EntityRepository {

    private DataSourceEndpoints dataSourceEndpoints;
    private RestTemplate restTemplate;

    @Autowired
    public EntityRepository(DataSourceEndpoints dataSourceEndpoints,
                            RestTemplate restTemplate) {
        this.dataSourceEndpoints = dataSourceEndpoints;
        this.restTemplate = restTemplate;
    }

    public Collection<Map<String, String>> findAllEntities(String ownerId) {

        var queryString = EntityQueryUtils.allEntitiesOwlProperties(ownerId);
        var selectResults = selectQuery(queryString, "READ: FAILED TO GET ALL ENTITIES PROPERTIES");

        return EntityUtils.getEntitiesPropertyMapFromSelectResults(selectResults);
    }

    public Collection<Map<String, String>> findAllEntities() {

        var queryString = EntityQueryUtils.allEntitiesOwlProperties();
        var selectResults = selectQuery(queryString, "READ: FAILED TO GET ALL ENTITIES PROPERTIES");

        return EntityUtils.getEntitiesPropertyMapFromSelectResults(selectResults);
    }

    public Collection<Map<String, String>> findEntity(String ownerId, String entityId) {

        var queryString = EntityQueryUtils.entityOwlProperties(ownerId, entityId);
        var selectResults = selectQuery(queryString, "READ: FAILED TO GET ENTITY PROPERTIES");

        return EntityUtils.getEntitiesPropertyMapFromSelectResults(selectResults);
    }

    public Collection<Map<String, String>> findEntity(String entityId) {

        var queryString = EntityQueryUtils.entityOwlProperties(entityId);
        var selectResults = selectQuery(queryString, "READ: FAILED TO GET ENTITY PROPERTIES");

        return EntityUtils.getEntitiesPropertyMapFromSelectResults(selectResults);
    }

    public void save(Entity entity) {
        var updateString = EntityUpdateUtils.newEntity(entity);
        graphUpdate(updateString, "CREATE: FAILED NEW ENTITY SPARQL UPDATE");
    }

    public void update(Entity entity) {
        var updateString = EntityUpdateUtils.updateEntity(entity);
        graphUpdate(updateString, "UPDATE: FAILED UPDATE ENTITY SPARQL UPDATE");
    }

    public void delete(String id) {
        var graphUpdateString = EntityUpdateUtils.deleteEntity(id);
        graphUpdate(graphUpdateString, "DELETE: FAILED DELETE ENTITY SPARQL UPDATE");
    }

    public void delete(String ownerID, String id) {
        var graphUpdateString = EntityUpdateUtils.deleteEntity(ownerID, id);
        graphUpdate(graphUpdateString, "DELETE: FAILED DELETE ENTITY SPARQL UPDATE");
    }

    public Collection<Map<String, String>> findAllEntities(String owner, Collection<String> terms) {
        var queryString = EntityQueryUtils.allEntitiesOwlProperties(owner, terms);
        var selectResults = selectQuery(queryString, "READ: FAILED TO GET ALL ENTITY PROPERTIES FOR SEARCH TERMS");
        return EntityUtils.getEntitiesPropertyMapFromSelectResults(selectResults);
    }

    public Collection<Map<String, String>> findAllEntities(Collection<String> terms) {
        var queryString = EntityQueryUtils.allEntitiesOwlProperties(terms);
        var selectResults = selectQuery(queryString, "READ: FAILED TO GET ALL ENTITY PROPERTIES FOR SEARCH TERMS");
        return EntityUtils.getEntitiesPropertyMapFromSelectResults(selectResults);
    }

    private SelectResults selectQuery(String queryFormString, String contextErrorMessage) {

        var httpEntity = HttpEntityFactory.getHttpEntitySelectQuery(queryFormString);
        return query(httpEntity, contextErrorMessage);
    }

    private void graphUpdate(String updateFormString, String contextErrorMessage) {

        var urlString = this.dataSourceEndpoints.getUpdateRepositoryStatements();
        var httpEntity = HttpEntityFactory.getHttpEntityGraphUpdate(updateFormString);

        try {
            this.restTemplate.postForEntity(urlString, httpEntity, String.class);
        } catch (RestClientException failedPostSparqlUpdate) {
            throw new EntityRepositoryException(contextErrorMessage, failedPostSparqlUpdate);
        }
    }

    private SelectResults query(HttpEntity httpEntity, String contextErrorMessage) {

        var urlString = this.dataSourceEndpoints.getQueryRepositoryStatements();

        SelectResults response;
        try {
            response = this.restTemplate.postForObject(urlString, httpEntity, SelectResults.class);
        } catch (RestClientException failedPostSparqlQuery) {
            throw new EntityRepositoryException(contextErrorMessage, failedPostSparqlQuery);
        }
        return response;
    }


}
