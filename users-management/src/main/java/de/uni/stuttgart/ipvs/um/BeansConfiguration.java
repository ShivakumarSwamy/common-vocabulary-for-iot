package de.uni.stuttgart.ipvs.um;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import de.uni.stuttgart.ipvs.ilv.config.DataSourceEndpoints;
import de.uni.stuttgart.ipvs.ilv.config.DataSourceProperties;
import de.uni.stuttgart.ipvs.ilv.repository.service.RepositoryService;
import de.uni.stuttgart.ipvs.um.config.UsersDataSourceEndpoints;
import de.uni.stuttgart.ipvs.um.config.UsersDataSourceProperties;

@Configuration
@EnableConfigurationProperties({UsersDataSourceProperties.class, UsersDataSourceEndpoints.class})
@Slf4j
public class BeansConfiguration {

    @Value("${force-initial-load:false}")
    private boolean forceInitialLoad;

    private final DataSourceProperties dataSourceProperties;
    private final DataSourceEndpoints dataSourceEndpoints;

    @Autowired
    public BeansConfiguration(DataSourceProperties dataSourceProperties,
                              DataSourceEndpoints dataSourceEndpoints) {
        this.dataSourceProperties = dataSourceProperties;
        this.dataSourceEndpoints = dataSourceEndpoints;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().rootUri(this.dataSourceProperties.getRootUrl()).build();
    }

    @Bean
    public CommandLineRunner load() {
        return args -> setupRepository();
    }

    private void setupRepository() {

        log.debug("FORCE INITIAL LOAD " + this.forceInitialLoad);
        var repositoryService =
                new RepositoryService(this.dataSourceProperties, this.dataSourceEndpoints, restTemplate());

        if (!repositoryService.hasRepository()) repositoryService.createRepositoryWithInitialLoad();

        if (this.forceInitialLoad) repositoryService.onlyInitialLoad();

    }


}
