package de.uni.stuttgart.ipvs.um.users.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import de.uni.stuttgart.ipvs.um.users.dto.UserCreateDTO;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
public class UserDetailsImpl implements UserDetails {

    private String id;
    private String username;

    @JsonIgnore
    @ToString.Exclude
    private String password;

    private String role;

    @JsonIgnore
    @ToString.Exclude
    private Collection<SimpleGrantedAuthority> authorities;

    public static UserDetailsImpl buildFromDTO(UserCreateDTO userCreateDTO) {

        var bcyrptString = new BCryptPasswordEncoder().encode(userCreateDTO.getPassword());
        userCreateDTO.eraseCredentials();

        var user = new UserDetailsImpl();
        user.setPassword(bcyrptString);
        user.setId(UUID.randomUUID().toString());
        user.setUsername(userCreateDTO.getUsername());
        user.setRole(userCreateDTO.getRole().toLowerCase());

        return user;
    }

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
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
}
