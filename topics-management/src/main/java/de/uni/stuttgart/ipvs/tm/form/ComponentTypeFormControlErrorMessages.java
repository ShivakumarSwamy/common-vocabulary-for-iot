package de.uni.stuttgart.ipvs.tm.form;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "error.message.hardware-type.form-control", ignoreUnknownFields = false)
@Data
public class HardwareTypeFormControlErrorMessages {

    private String hardwareComponent;

    private String category;

    private String label;

    private String comment;

}
