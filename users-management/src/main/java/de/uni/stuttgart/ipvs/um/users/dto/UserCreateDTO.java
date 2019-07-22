package de.uni.stuttgart.ipvs.um.users.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import org.springframework.security.core.CredentialsContainer;

import de.uni.stuttgart.ipvs.um.auth.PrincipalCredentials;

@Data
public class UserCreateDTO implements PrincipalCredentials, CredentialsContainer {


    private String username;

    @ToString.Exclude
    private String password;

    @ApiModelProperty(value = "Role of the user", allowableValues = "manager,consumer")
    private String role;

    @Override
    public void eraseCredentials() {
        this.password = "";
    }
}
