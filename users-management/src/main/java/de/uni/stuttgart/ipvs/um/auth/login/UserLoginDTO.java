package de.uni.stuttgart.ipvs.um.auth.login;

import lombok.Data;
import lombok.ToString;

import org.springframework.security.core.CredentialsContainer;

import de.uni.stuttgart.ipvs.um.auth.PrincipalCredentials;

@Data
public class UserLoginDTO implements PrincipalCredentials, CredentialsContainer {

    private String username;

    @ToString.Exclude
    private String password;

    @Override
    public void eraseCredentials() {
        this.password = "";
    }
}
