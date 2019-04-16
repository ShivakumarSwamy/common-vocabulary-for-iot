package de.uni.stuttgart.ipvs.um.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

import de.uni.stuttgart.ipvs.ilv.config.DataSourceEndpoints;

@ConfigurationProperties(prefix = "data.source.endpoints", ignoreUnknownFields = false)
@Data
public class UsersDataSourceEndpoints implements DataSourceEndpoints {

    private String createRepository;

    private String deleteRepository;

    private String readAllRepository;

    private String updateRepositoryStatements;

    private String queryRepositoryStatements;
}
