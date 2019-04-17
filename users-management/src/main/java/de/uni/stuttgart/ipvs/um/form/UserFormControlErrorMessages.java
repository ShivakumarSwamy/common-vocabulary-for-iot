package de.uni.stuttgart.ipvs.um.form;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "error.message.user.form-control", ignoreUnknownFields = false)
@Data
public class UserFormControlErrorMessages {

    private String username;

    private String password;

    private String role;
    
}
