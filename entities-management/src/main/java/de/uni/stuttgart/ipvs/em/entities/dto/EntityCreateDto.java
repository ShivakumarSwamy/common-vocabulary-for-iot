package de.uni.stuttgart.ipvs.em.entities.dto;

import lombok.Data;

import de.uni.stuttgart.ipvs.em.entities.properties.HardwareProperties;
import de.uni.stuttgart.ipvs.em.entities.properties.LocationProperties;
import de.uni.stuttgart.ipvs.em.entities.properties.MessageProperties;
import de.uni.stuttgart.ipvs.em.entities.properties.TopicProperties;

@Data
public class EntityCreateDto
        implements TopicProperties, MessageProperties, HardwareProperties, LocationProperties {

    private String path;
    private String middlewareEndpoint;
    private String dataType;
    private String protocol;
    private String topicType;

    private String hardwareType;
    private String unit;

    private String messageFormat;
    private String metaModelType;
    private String metaModel;

    private String country;
    private String state;
    private String city;
    private String street;
    private String point;
}

