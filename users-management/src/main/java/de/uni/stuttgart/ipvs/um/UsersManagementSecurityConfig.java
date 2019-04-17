package de.uni.stuttgart.ipvs.um;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.keys.HmacKey;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyAuthoritiesMapper;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import de.uni.stuttgart.ipvs.um.auth.login.LoginAuthenticationJwtTokenGeneratorFilter;
import de.uni.stuttgart.ipvs.um.auth.token.JwtHttpBearerAuthenticationEntryPoint;
import de.uni.stuttgart.ipvs.um.auth.token.JwtHttpBearerFilter;
import de.uni.stuttgart.ipvs.um.form.UserFormModelValidation;
import de.uni.stuttgart.ipvs.um.users.service.UserDetailsServiceImpl;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import static org.jose4j.jwa.AlgorithmConstraints.ConstraintType.WHITELIST;
import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

@EnableWebSecurity
public class UsersManagementSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserFormModelValidation userFormValidation;

    @Autowired
    public UsersManagementSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl,
                                         UserFormModelValidation userFormValidation) {

        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.userFormValidation = userFormValidation;
    }

    private static RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(
                "ROLE_ADMIN > ROLE_MANAGER\n"
                        + "ROLE_MANAGER > ROLE_CONSUMER\n"
                        + "ROLE_CONSUMER > ROLE_AUTHENTICATED"
        );

        return roleHierarchy;
    }

    private static HmacKey hmacKey() {
        return new HmacKey("my-very-precious-secret-key-hmac-key".getBytes());
    }

    private static JsonWebSignature jsonWebSignature() {
        JsonWebSignature jws = new JsonWebSignature();
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setHeader(HeaderParameterNames.TYPE, "jwt");
        jws.setKey(hmacKey());
        return jws;

    }

    private static JwtConsumer jwtConsumer() {
        JwtConsumerBuilder builder = new JwtConsumerBuilder();

        builder.setRequireSubject()
                .setRequireExpirationTime()
                .setVerificationKey(hmacKey())
                .setJwsAlgorithmConstraints(new AlgorithmConstraints(WHITELIST, HMAC_SHA256));

        return builder.build();
    }

    private void addJwtTokenValidatorFilter(HttpSecurity http) throws Exception {
        http.addFilterAt(
                new JwtHttpBearerFilter(
                        authenticationManager(), new JwtHttpBearerAuthenticationEntryPoint(),
                        jwtConsumer(), roleHierarchy()
                ),
                BasicAuthenticationFilter.class
        );
    }

    private void addLoginFilter(HttpSecurity http) throws Exception {
        http.addFilterAt(
                new LoginAuthenticationJwtTokenGeneratorFilter(
                        authenticationManager(), jsonWebSignature(), this.userFormValidation),
                UsernamePasswordAuthenticationFilter.class
        );
    }

    private void disableNotNeededFilters(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .logout().disable()
                .sessionManagement().disable()
                .cors().disable()
                .rememberMe().disable()
                .logout().disable()
                .x509().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        daoAuthenticationProvider.setAuthoritiesMapper(new RoleHierarchyAuthoritiesMapper(roleHierarchy()));

        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(POST, "/api/users").permitAll()
                .antMatchers(GET, "/api/users").hasRole("AUTHENTICATED")
                .antMatchers(GET, "/api/admin/users").hasRole("ADMIN");

        disableNotNeededFilters(http);
        addLoginFilter(http);
        addJwtTokenValidatorFilter(http);
    }
}
