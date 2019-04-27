package de.uni.stuttgart.ipvs.tm.topics.dto;

import lombok.Data;

import de.uni.stuttgart.ipvs.tm.topics.properties.*;

@Data
public class TopicEditDTO
        implements EntityProperties, TopicProperties, MessageProperties, HardwareProperties, LocationProperties {

    private String id = "";
    private String owner;

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

