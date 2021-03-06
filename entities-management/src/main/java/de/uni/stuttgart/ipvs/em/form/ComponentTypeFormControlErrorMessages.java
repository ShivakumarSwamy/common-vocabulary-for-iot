package de.uni.stuttgart.ipvs.em.form;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "error.message.component-type.form-control", ignoreUnknownFields = false)
@Data
public class ComponentTypeFormControlErrorMessages {

    private String component;
    private String componentCategory;

    private String label;
    private String comment;

}
