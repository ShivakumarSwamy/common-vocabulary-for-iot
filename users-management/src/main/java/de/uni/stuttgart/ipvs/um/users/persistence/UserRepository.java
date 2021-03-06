package de.uni.stuttgart.ipvs.um.users.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.uni.stuttgart.ipvs.ilv.HttpEntityFactory;
import de.uni.stuttgart.ipvs.ilv.config.DataSourceEndpoints;
import de.uni.stuttgart.ipvs.results.SelectResults;

import java.util.Collection;
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

    public boolean isUsernameExists(String username) {

        var askQueryString = UserQueryUtils.askUsernameExists(username);
        var httpEntity = HttpEntityFactory.getHttpEntityAskQuery(askQueryString);

        var stringBoolean = query(httpEntity, String.class, "ASK USER: FAILED TO ASK USERNAME EXISTS");

        return stringBoolean == null || stringBoolean.equals("true");
    }

    public void save(UserDetailsImpl userDetails) {

        var graphUpdateFormString = UserUpdateUtils.newUserDetails(userDetails);
        graphUpdate(graphUpdateFormString, "CREATE: NEW USER");
    }

    public Collection<Map<String, String>> findAllUsers() {

        var allUsersQueryString = UserQueryUtils.findAllUsers();
        var selectResults = selectQuery(allUsersQueryString, "READ: ALL USERS");

        return UserUtils.getUsersPropertyMapFromSelectResults(selectResults);
    }

    public Collection<Map<String, String>> findUserByUserId(String id) {

        var userQueryString = UserQueryUtils.findUserByUserId(id);
        var selectResults = selectQuery(userQueryString, "READ: A USER");

        return UserUtils.getUsersPropertyMapFromSelectResults(selectResults);
    }

    public UserDetailsImpl findUserByUsername(String username) {

        var userQueryString = UserQueryUtils.findUserByUsername(username);
        var selectResults = selectQuery(userQueryString, "READ: A USER");

        return UserUtils.getUserFromSelectResults(selectResults);
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
            throw new UserRepositoryException(contextErrorMessage, failedPostSparqlUpdate);
        }
    }

    private <T> T query(HttpEntity httpEntity, Class<T> responseType, String errorMessage) {

        var urlString = this.dataSourceEndpoints.getQueryRepositoryStatements();
        T response;
        try {
            response = this.restTemplate.postForObject(urlString, httpEntity, responseType);
        } catch (RestClientException failedPostSparqlQuery) {
            throw new UserRepositoryException(errorMessage, failedPostSparqlQuery);
        }
        return response;
    }
}
