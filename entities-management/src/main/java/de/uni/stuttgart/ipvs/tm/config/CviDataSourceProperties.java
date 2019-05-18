package de.uni.stuttgart.ipvs.tm.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

import de.uni.stuttgart.ipvs.ilv.config.DataSourceProperties;

@ConfigurationProperties(prefix = "data.source.properties", ignoreUnknownFields = false)
@Data
public class CviDataSourceProperties implements DataSourceProperties {

    private String scheme;

    private String host;

    private String port;

    private String repositoryId;

    private String initialLoadFileLocation;

    @Override
    public String getRootUrl() {
        return this.scheme + "://" + this.host + ":" + this.port;
    }
}
