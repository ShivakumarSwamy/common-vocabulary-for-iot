package de.uni.stuttgart.ipvs.tm.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

import de.uni.stuttgart.ipvs.ilv.config.DataSourceEndpoints;

@ConfigurationProperties(prefix = "data.source.endpoints", ignoreUnknownFields = false)
@Data
public class CviDataSourceEndpoints implements DataSourceEndpoints {

    private String createRepository;

    private String deleteRepository;

    private String readAllRepository;

    private String updateRepositoryStatements;

    private String queryRepositoryStatements;
}

