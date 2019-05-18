package de.uni.stuttgart.ipvs.em.entities.form;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "error.message.entity.form-control", ignoreUnknownFields = false)
@Data
public class EntityFormControlErrorMessages {

    private String path;
    private String protocol;
    private String middlewareEndpoint;
    private String topicType;
    private String dataType;

    private String unit;
    private String hardwareType;

    private String messageFormat;
    private String metaModelType;
    private String metaModel;

    private String country;
    private String state;
    private String city;
    private String street;
    private String point;

}
