package de.uni.stuttgart.ipvs.ilv.config;

public interface DataSourceProperties {

    String getScheme();

    String getHost();

    String getPort();

    String getRootUrl();

    String getRepositoryId();

    String getInitialLoadFileLocation();

}
