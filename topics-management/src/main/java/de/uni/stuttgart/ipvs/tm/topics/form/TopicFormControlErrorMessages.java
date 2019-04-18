package de.uni.stuttgart.ipvs.tm.topics.form;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "error.message.topic.form-control", ignoreUnknownFields = false)
@Data
public class TopicFormControlErrorMessages {

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

}
