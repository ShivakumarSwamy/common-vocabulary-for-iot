package de.uni.stuttgart.ipvs.um.users.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.uni.stuttgart.ipvs.ilv.HttpEntityFactory;
import de.uni.stuttgart.ipvs.ilv.config.DataSourceEndpoints;
import de.uni.stuttgart.ipvs.results.SelectResults;
import de.uni.stuttgart.ipvs.results.VariableBinding;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final RestTemplate restTemplate;
    private final DataSourceEndpoints dataSourceEndpoints;

    @Autowired
    public UserRepository(RestTemplate restTemplate,
                          DataSourceEndpoints dataSourceEndpoints) {
        this.restTemplate = restTemplate;
        this.dataSourceEndpoints = dataSourceEndpoints;
    }

    public Map<String, List<Map<String, VariableBinding>>> findAllUsers() {
        var allUsersQueryString = UserQueryUtils.findAllUsers();

        var selectResults = selectQuery(allUsersQueryString, "READ: ALL USERS");

        return UserUtils.getUsersFromSelectResults(selectResults);
    }

    public void save(UserDetailsImpl userDetails) {

    }

    private SelectResults selectQuery(String queryFormString, String contextErrorMessage) {

        var httpEntity = HttpEntityFactory.getHttpEntitySelectQuery(queryFormString);
        return query(httpEntity, SelectResults.class, contextErrorMessage);

    }

    private <T> T query(HttpEntity httpEntity, Class<T> responseType, String errorMessage) {

        var urlString = dataSourceEndpoints.getQueryRepositoryStatements();
        T response;
        try {
            response = restTemplate.postForObject(urlString, httpEntity, responseType);
        } catch (RestClientException failedPostSparqlQuery) {
            throw new UserRepositoryException(errorMessage, failedPostSparqlQuery);
        }
        return response;
    }
}
