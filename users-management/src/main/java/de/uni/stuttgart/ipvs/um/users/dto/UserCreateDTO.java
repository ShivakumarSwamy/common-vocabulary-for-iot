package de.uni.stuttgart.ipvs.um.users.dto;

import lombok.Data;
import lombok.ToString;

import org.springframework.security.core.CredentialsContainer;

@Data
public class UserCreateDTO implements PrincipalCredentials, CredentialsContainer {

    private String username;

    @ToString.Exclude
    private String password;
    private String role;

    @Override
    public void eraseCredentials() {
        this.password = "";
    }
}
