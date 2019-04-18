package de.uni.stuttgart.ipvs.tm.topics.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.uni.stuttgart.ipvs.ilv.HttpEntityFactory;
import de.uni.stuttgart.ipvs.ilv.config.DataSourceEndpoints;
import de.uni.stuttgart.ipvs.results.SelectResults;
import de.uni.stuttgart.ipvs.tm.topics.service.Topic;

import java.util.Collection;
import java.util.Map;

@Repository
public class TopicRepository {

    private DataSourceEndpoints dataSourceEndpoints;
    private RestTemplate restTemplate;

    @Autowired
    public TopicRepository(DataSourceEndpoints dataSourceEndpoints,
                           RestTemplate restTemplate) {
        this.dataSourceEndpoints = dataSourceEndpoints;
        this.restTemplate = restTemplate;
    }

    public Collection<Map<String, String>> findAllTopics(String ownerId) {
        var queryString = TopicQueryUtils.allTopicsOwlProperties(ownerId);
        var selectResults = selectQuery(queryString, "READ: FAILED TO GET ALL TOPICS PROPERTIES");

        return TopicUtils.getTopicsPropertyMapFromSelectResults(selectResults);
    }

    public Collection<Map<String, String>> findAllTopics() {
        var queryString = TopicQueryUtils.allTopicsOwlProperties();
        var selectResults = selectQuery(queryString, "READ: FAILED TO GET ALL TOPICS PROPERTIES");

        return TopicUtils.getTopicsPropertyMapFromSelectResults(selectResults);
    }

    public Collection<Map<String, String>> findTopic(String ownerId, String entityId) {
        var queryString = TopicQueryUtils.topicOwlProperties(ownerId, entityId);
        var selectResults = selectQuery(queryString, "READ: FAILED TO GET TOPIC PROPERTIES");
        return TopicUtils.getTopicsPropertyMapFromSelectResults(selectResults);
    }

    public Collection<Map<String, String>> findTopic(String entityId) {
        var queryString = TopicQueryUtils.topicOwlProperties(entityId);
        var selectResults = selectQuery(queryString, "READ: FAILED TO GET TOPIC PROPERTIES");
        return TopicUtils.getTopicsPropertyMapFromSelectResults(selectResults);
    }

    public void save(Topic topic) {
        var graphUpdateString = TopicUpdateUtils.newTopic(topic);
        graphUpdate(graphUpdateString, "CREATE: FAILED NEW TOPIC SPARQL UPDATE");
    }

    public void update(Topic topic) {
        var graphUpdateString = TopicUpdateUtils.updateTopic(topic);
        graphUpdate(graphUpdateString, "UPDATE: FAILED UPDATE TOPIC SPARQL UPDATE");
    }


    public void delete(String id) {
        var graphUpdateString = TopicUpdateUtils.deleteTopic(id);
        graphUpdate(graphUpdateString, "DELETE: FAILED DELETE TOPIC SPARQL UPDATE");
    }

    public void delete(String ownerID, String id) {
        var graphUpdateString = TopicUpdateUtils.deleteTopic(ownerID, id);
        graphUpdate(graphUpdateString, "DELETE: FAILED DELETE TOPIC SPARQL UPDATE");
    }

    private SelectResults selectQuery(String queryFormString, String contextErrorMessage) {

        var httpEntity = HttpEntityFactory.getHttpEntitySelectQuery(queryFormString);
        return query(httpEntity, SelectResults.class, contextErrorMessage);

    }

    private void graphUpdate(String graphUpdateFormString, String contextErrorMessage) {

        var httpEntity = HttpEntityFactory.getHttpEntityGraphUpdate(graphUpdateFormString);
        var urlString = this.dataSourceEndpoints.getUpdateRepositoryStatements();

        try {
            this.restTemplate.postForEntity(urlString, httpEntity, String.class);
        } catch (RestClientException failedPostSparqlUpdate) {
            throw new TopicRepositoryException(contextErrorMessage, failedPostSparqlUpdate);
        }
    }

    private <T> T query(HttpEntity httpEntity, Class<T> responseType, String errorMessage) {

        var urlString = this.dataSourceEndpoints.getQueryRepositoryStatements();
        T response;
        try {
            response = this.restTemplate.postForObject(urlString, httpEntity, responseType);
        } catch (RestClientException failedPostSparqlQuery) {
            throw new TopicRepositoryException(errorMessage, failedPostSparqlQuery);
        }
        return response;
    }

    public Collection<Map<String, String>> findAllTopics(String owner, Collection<String> terms) {
        var queryString = TopicQueryUtils.allTopicsOwlProperties(owner, terms);
        var selectResults = selectQuery(queryString, "READ: FAILED TO GET ALL TOPIC PROPERTIES FOR SEARCH TERMS");
        return TopicUtils.getTopicsPropertyMapFromSelectResults(selectResults);
    }

    public Collection<Map<String, String>> findAllTopics(Collection<String> terms) {
        var queryString = TopicQueryUtils.allTopicsOwlProperties(terms);
        var selectResults = selectQuery(queryString, "READ: FAILED TO GET ALL TOPIC PROPERTIES FOR SEARCH TERMS");
        return TopicUtils.getTopicsPropertyMapFromSelectResults(selectResults);
    }


//
//    private QueryRawResponse query(String queryString, String errorMessage) {
//
//        var httpEntity = HttpEntityFactory.getHttpEntityForQuery(queryString);
//        return query(httpEntity, QueryRawResponse.class, errorMessage);
//    }
//
//    private void sparqlUpdate(String updateString, String contextMessage) {
//
//        var httpEntity = getHttpEntityForSparqlUpdate(updateString);
//        var urlString = dataSourceEndpoints.statements();
//
//        try {
//            log.debug("3. Load topics entity triples to triple store");
//            restTemplate.postForEntity(urlString, httpEntity, String.class);
//        } catch (RestClientException failedPostSparqlUpdate) {
//            log.error("4. update load failed");
//            throw new TopicRepositoryException(contextMessage, failedPostSparqlUpdate);
//        }
//        log.debug("4. update load succeeded");
//    }
//
//    private <T> T query(HttpEntity httpEntity, Class<T> responseType, String errorMessage) {
//
//        var urlString = dataSourceEndpoints.repository();
//        T response;
//        try {
//            response = restTemplate.postForObject(urlString, httpEntity, responseType);
//        } catch (RestClientException failedPostSparqlQuery) {
//            throw new TopicRepositoryException(errorMessage, failedPostSparqlQuery);
//        }
//        return response;
//    }

    //    public boolean isTopicEntityExists(String path, String protocol, String lowerCaseHardwareType) {
//        var queryString = TopicQueryUtils.askTopicEntityExists(path, protocol, lowerCaseHardwareType);
//        return ask(queryString, "Failed to ask for topics entity exists ");
//    }
//
//    public boolean isTopicEntitySubClassOfHardware(String lowerCaseHardwareTypePresented,
//                                                   String hardwareType) {
//        var queryString = askHardwareSubClassOfSensorOrActuator(lowerCaseHardwareTypePresented, hardwareType);
//        return ask(queryString, "Failed request isTopicEntitySubClassOfHardware");
//    }
//
//    public boolean isTopicEntityHardwareExists(String hardwareTypePresented) {
//
//        var queryString =
//                TopicQueryUtils.askTopicEntityHardwareExists(hardwareTypePresented);
//        return ask(queryString, "Failed request isTopicEntityHardwareExists");
//    }


//    private boolean ask(String queryString, String errorMessage) {
//        log.debug("ask query: \n" + queryString);
//        var httpEntity = HttpEntityFactory.getHttpEntityForAskQuery(queryString);
//
//        String stringBoolean = query(httpEntity, String.class, errorMessage);
//        return stringBoolean == null || stringBoolean.equals("true");
//    }
}
