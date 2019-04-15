package de.uni.stuttgart.ipvs.config;

public interface DataSourceProperties {

    String getScheme();

    String getHost();

    String getPort();

    String getRootUrl();

    String getRepositoryId();

    String getInitialLoadFileLocation();

}
