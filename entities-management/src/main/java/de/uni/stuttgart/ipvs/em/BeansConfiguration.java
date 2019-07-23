package de.uni.stuttgart.ipvs.em;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import de.uni.stuttgart.ipvs.em.config.CviDataSourceEndpoints;
import de.uni.stuttgart.ipvs.em.config.CviDataSourceProperties;
import de.uni.stuttgart.ipvs.ilv.config.DataSourceEndpoints;
import de.uni.stuttgart.ipvs.ilv.config.DataSourceProperties;
import de.uni.stuttgart.ipvs.ilv.repository.service.RepositoryService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Configuration
@EnableConfigurationProperties({CviDataSourceProperties.class, CviDataSourceEndpoints.class})
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

    @Bean
    public Docket apiDocs() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(entitiesApiInfo())
                .tags(
                        new Tag("Admin Users", ""),new Tag("Manager Users", ""),
                        new Tag("Consumer Users", ""), new Tag("All Users", "")
                )
                .useDefaultResponseMessages(false)
                .securitySchemes(List.of(apiKey()))
                .securityContexts(List.of(securityContext()))
                .globalResponseMessage(GET, List.of(getGlobal500ResponseMessage()))
                .globalResponseMessage(POST, List.of(getGlobal500ResponseMessage(), getGlobal400ResponseMessage()))
                .globalResponseMessage(PUT, List.of(getGlobal500ResponseMessage(), getGlobal400ResponseMessage()))
                .globalResponseMessage(DELETE, List.of(getGlobal500ResponseMessage()))
                .select()
                .build();
    }

    private ResponseMessage getGlobal400ResponseMessage() {
        return new ResponseMessageBuilder()
                .code(400)
                .message("A JSON Error Message with value present in a message key of a JSON object, " +
                        "Message consists of form error")
                .build();
    }

    private ResponseMessage getGlobal500ResponseMessage() {
        return new ResponseMessageBuilder()
                .code(500)
                .message("Sorry internal server error, please try again later in a message key of a JSON object")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT Token Auth", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("Token Auth", authorizationScopes));
    }

    private ApiInfo entitiesApiInfo() {
        return new ApiInfo(
                "Entities Management API Documentation", "", "1.0",
                "", new Contact("", "", "st152495@stud.uni-stuttgart.de"),
                "", "",
                new ArrayList<>()
        );
    }

}
