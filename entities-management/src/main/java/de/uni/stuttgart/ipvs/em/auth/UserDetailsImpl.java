package de.uni.stuttgart.ipvs.em.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class UserDetailsImpl implements UserDetails, CredentialsContainer {

    private String id;
    private String username;

    @JsonIgnore
    @ToString.Exclude
    private String password;

    private String role;

    @JsonIgnore
    @ToString.Exclude
    private Collection<SimpleGrantedAuthority> authorities;


    public void setRole(@NonNull String role) {
        this.role = role;
        var roleUppercase = role.toUpperCase();
        // for authentication
        this.setAuthorities(List.of(new SimpleGrantedAuthority("ROLE_" + roleUppercase)));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void eraseCredentials() {
        this.password = "";
    }
}
