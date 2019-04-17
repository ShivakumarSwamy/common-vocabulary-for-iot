package de.uni.stuttgart.ipvs.tm.persistence;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
