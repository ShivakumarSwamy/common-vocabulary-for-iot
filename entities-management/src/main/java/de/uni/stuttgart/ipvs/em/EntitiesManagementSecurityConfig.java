package de.uni.stuttgart.ipvs.em;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import de.uni.stuttgart.ipvs.em.auth.token.JwtHttpBearerAuthenticationEntryPoint;
import de.uni.stuttgart.ipvs.em.auth.token.JwtHttpBearerFilter;

import static org.springframework.http.HttpMethod.POST;

import static org.jose4j.jwa.AlgorithmConstraints.ConstraintType.WHITELIST;
import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

@EnableWebSecurity
public class EntitiesManagementSecurityConfig extends WebSecurityConfigurerAdapter {

    private static HmacKey hmacKey() {
        return new HmacKey("my-very-precious-secret-key-hmac-key".getBytes());
    }

    private static JwtConsumer jwtConsumer() {
        JwtConsumerBuilder builder = new JwtConsumerBuilder();

        builder.setRequireSubject()
                .setRequireExpirationTime()
                .setVerificationKey(hmacKey())
                .setJwsAlgorithmConstraints(new AlgorithmConstraints(WHITELIST, HMAC_SHA256));

        return builder.build();
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

    private void addJwtTokenValidatorFilter(HttpSecurity http) throws Exception {
        http.addFilterAt(
                new JwtHttpBearerFilter(authenticationManager(),
                        new JwtHttpBearerAuthenticationEntryPoint(), jwtConsumer()
                ),
                BasicAuthenticationFilter.class
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/manager/**").hasRole("MANAGER")
                .antMatchers("/api/consumer/**").hasRole("CONSUMER")
                .antMatchers(POST, "/api/meaning/terms").hasAnyRole("ADMIN", "MANAGER", "CONSUMER");

        this.disableNotNeededFilters(http);
        this.addJwtTokenValidatorFilter(http);
    }
}
