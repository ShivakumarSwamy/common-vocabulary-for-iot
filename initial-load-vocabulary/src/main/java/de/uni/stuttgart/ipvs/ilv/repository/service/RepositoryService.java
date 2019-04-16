package de.uni.stuttgart.ipvs.ilv.repository.service;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.uni.stuttgart.ipvs.ilv.FileUtils;
import de.uni.stuttgart.ipvs.ilv.HttpEntityFactory;
import de.uni.stuttgart.ipvs.ilv.config.DataSourceEndpoints;
import de.uni.stuttgart.ipvs.ilv.config.DataSourceProperties;
import de.uni.stuttgart.ipvs.ilv.repository.utils.RepositoryUtils;
import de.uni.stuttgart.ipvs.results.SelectResults;

import java.io.IOException;

@Slf4j
public class RepositoryService {

    private DataSourceProperties dataSourceProperties;
    private DataSourceEndpoints dataSourceEndpoints;
    private RestTemplate restTemplate;

    public RepositoryService(@NonNull DataSourceProperties dataSourceProperties,
                             @NonNull DataSourceEndpoints dataSourceEndpoints,
                             @NonNull RestTemplate restTemplate) {

        this.dataSourceProperties = dataSourceProperties;
        this.dataSourceEndpoints = dataSourceEndpoints;
        this.restTemplate = restTemplate;
    }

    public boolean hasRepository() {

        SelectResults selectResults;
        try {
            selectResults =
                    this.restTemplate.getForObject(this.dataSourceEndpoints.getReadAllRepository(), SelectResults.class);

        } catch (RestClientException failedGetAllRepositories) {
            throw new RepositoryServiceException("READ: FAILED TO READ ALL REPOSITORIES", failedGetAllRepositories);
        }

        selectResults = (selectResults != null ? selectResults : new SelectResults());

        return RepositoryUtils.checkRepositoryIdInSelectResults(selectResults, this.dataSourceProperties.getRepositoryId());
    }


    public void createRepositoryWithInitialLoad() {
        createRepositoryWithInitialLoad(true);
    }

    public void onlyInitialLoad() {
        createRepositoryWithInitialLoad(false);
    }

    private void createRepositoryWithInitialLoad(boolean createRepository) {

        String updateFormString;
        try {
            updateFormString = FileUtils.contentsAsStringFromFileInClasspath(
                    this.dataSourceProperties.getInitialLoadFileLocation());

        } catch (IOException failedReadFile) {
            throw new RepositoryServiceException("FAILED TO READ INITIAL LOAD FILE", failedReadFile);
        }

        if (createRepository) createRepository();
        initialLoad(updateFormString, createRepository);
    }


    private void createRepository() {

        var repositoryConfigDetails =
                RepositoryUtils.defaultRepositoryConfigDetails(this.dataSourceProperties.getRepositoryId());

        try {
            this.restTemplate.put(this.dataSourceEndpoints.getCreateRepository(), repositoryConfigDetails);
        } catch (RestClientException failedPutCreateRepository) {
            throw new RepositoryServiceException("CREATE: FAILED TO CREATE REPOSITORY", failedPutCreateRepository);
        }

        log.info("CREATED REPOSITORY " + this.dataSourceProperties.getRepositoryId());
    }

    private void initialLoad(String updateFormString, boolean deleteRepository) {

        var httpEntity = HttpEntityFactory.getHttpEntityGraphUpdate(updateFormString);

        try {
            this.restTemplate.postForObject(
                    this.dataSourceEndpoints.getUpdateRepositoryStatements(), httpEntity, String.class);

        } catch (RestClientException failedPostInitialLoad) {
            if (deleteRepository) deleteRepositoryIfInitialLoadFails();
            throw new RepositoryServiceException(
                    "PUT: FAILED INITIAL LOAD, REPOSITORY ALSO DELETED IF ASKED TO CREATE", failedPostInitialLoad);
        }

        log.info("INITIAL LOAD DONE FILE:" + this.dataSourceProperties.getInitialLoadFileLocation());
    }

    private void deleteRepositoryIfInitialLoadFails() {

        try {
            this.restTemplate.delete(this.dataSourceEndpoints.getDeleteRepository());
        } catch (RestClientException failedDeleteRepositoryRequest) {
            throw new RepositoryServiceException(
                    "DELETE: FAILED DELETE REPOSITORY, WHEN INITIAL LOAD FAILED ", failedDeleteRepositoryRequest);
        }
    }

}
