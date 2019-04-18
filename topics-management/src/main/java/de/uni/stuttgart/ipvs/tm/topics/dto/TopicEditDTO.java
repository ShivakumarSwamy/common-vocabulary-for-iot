package de.uni.stuttgart.ipvs.tm.topics.dto;

import lombok.Data;

import de.uni.stuttgart.ipvs.tm.topics.properties.EntityProperties;
import de.uni.stuttgart.ipvs.tm.topics.properties.HardwareProperties;
import de.uni.stuttgart.ipvs.tm.topics.properties.MessageProperties;
import de.uni.stuttgart.ipvs.tm.topics.properties.TopicProperties;

@Data
public class TopicEditDTO implements EntityProperties, TopicProperties, MessageProperties, HardwareProperties {

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
}

