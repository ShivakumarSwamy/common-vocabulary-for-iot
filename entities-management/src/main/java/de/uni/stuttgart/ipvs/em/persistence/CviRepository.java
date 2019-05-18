package de.uni.stuttgart.ipvs.em.persistence;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.uni.stuttgart.ipvs.em.service.ComponentType;
import de.uni.stuttgart.ipvs.ilv.HttpEntityFactory;
import de.uni.stuttgart.ipvs.ilv.config.DataSourceEndpoints;
import de.uni.stuttgart.ipvs.results.SelectResults;

import java.util.Collection;

@Repository
@Slf4j
public class CviRepository {

    private final RestTemplate restTemplate;
    private final DataSourceEndpoints dataSourceEndpoints;


    public CviRepository(RestTemplate restTemplate, DataSourceEndpoints dataSourceEndpoints) {
        this.restTemplate = restTemplate;
        this.dataSourceEndpoints = dataSourceEndpoints;
    }

    public SelectResults searchMeaningOfTerms(Collection<String> terms) {

        var queryString = CviQueryUtils.meaningOfTerms(terms);
        return selectQuery(queryString, "READ: FAILED TO GET SEARCH TERMS MEANING");
    }

    public SelectResults findAllComponentTypes() {
        var queryFormString = CviQueryUtils.allComponentTypes();
        return selectQuery(queryFormString, "READ: ALL COMPONENT TYPES", false);
    }

    public void save(ComponentType componentType) {
        var graphUpdateFormString = CviUpdateUtils.newComponentType(componentType);
        graphUpdate(graphUpdateFormString, "CREATE: NEW COMPONENT TYPE");
    }

    private SelectResults selectQuery(String queryFormString, String contextErrorMessage) {
        return selectQuery(queryFormString, contextErrorMessage, true);
    }

    private SelectResults selectQuery(String queryFormString, String contextErrorMessage, boolean infer) {

        var httpEntity = HttpEntityFactory.getHttpEntitySelectQuery(queryFormString, infer);
        return query(httpEntity, SelectResults.class, contextErrorMessage);

    }

    private void graphUpdate(String graphUpdateFormString, String contextErrorMessage) {

        var httpEntity = HttpEntityFactory.getHttpEntityGraphUpdate(graphUpdateFormString);
        var urlString = this.dataSourceEndpoints.getUpdateRepositoryStatements();

        try {
            this.restTemplate.postForEntity(urlString, httpEntity, String.class);
        } catch (RestClientException failedPostSparqlUpdate) {
            throw new CviRepositoryException(contextErrorMessage, failedPostSparqlUpdate);
        }
    }

    private <T> T query(HttpEntity httpEntity, Class<T> responseType, String errorMessage) {

        var urlString = this.dataSourceEndpoints.getQueryRepositoryStatements();
        T response;
        try {
            response = this.restTemplate.postForObject(urlString, httpEntity, responseType);
        } catch (RestClientException failedPostSparqlQuery) {
            throw new CviRepositoryException(errorMessage, failedPostSparqlQuery);
        }
        return response;
    }

}
