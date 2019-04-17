package de.uni.stuttgart.ipvs.tm.auth.token;

import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;

import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import de.uni.stuttgart.ipvs.tm.auth.UserDetailsImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static de.uni.stuttgart.ipvs.tm.auth.AuthConstants.HEADER_NAME_AUTHORIZATION;
import static de.uni.stuttgart.ipvs.tm.auth.AuthConstants.JWT_TOKEN_PREFIX;

@Slf4j
public class JwtHttpBearerFilter extends BasicAuthenticationFilter {

    private final JwtConsumer jwtConsumer;
    private final RoleHierarchy roleHierarchy;

    public JwtHttpBearerFilter(AuthenticationManager authenticationManager,
                               AuthenticationEntryPoint authenticationEntryPoint,
                               JwtConsumer jwtConsumer,
                               RoleHierarchy roleHierarchy) {

        super(authenticationManager, authenticationEntryPoint);
        this.jwtConsumer = jwtConsumer;
        this.roleHierarchy = roleHierarchy;
    }

    private static UserDetailsImpl getUser(JwtClaims claims) throws MalformedClaimException {

        var authenticatedUser = new UserDetailsImpl();

        var authUserId = claims.getSubject();
        authenticatedUser.setId(authUserId);

        var role = claims.getStringClaimValue("role");
        authenticatedUser.setRole(role);

        return authenticatedUser;

    }

    private static JwtClaims validateClaimsOfToken(String authorizationHeader, JwtConsumer jwtConsumer)
            throws InvalidJwtException {
        var jwtToken = authorizationHeader.replaceFirst(JWT_TOKEN_PREFIX, "");
        var claims = jwtConsumer.processToClaims(jwtToken);
        log.debug("JWT TOKEN CLAIMS VALID");
        return claims;
    }

    private UsernamePasswordAuthenticationToken getAuthenticationObject(String authorizationHeader) {

        try {
            JwtClaims claims = validateClaimsOfToken(authorizationHeader, this.jwtConsumer);
            var authUser = getUser(claims);

            var reachableGrantedAuthorities = this.roleHierarchy.getReachableGrantedAuthorities(authUser.getAuthorities());
            return new UsernamePasswordAuthenticationToken(authUser, null, reachableGrantedAuthorities);

        } catch (InvalidJwtException | MalformedClaimException e) {
            log.error("JWT TOKEN CLAIMS INVALID");
            throw new BadCredentialsException("TOKEN INVALID", e);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authorizationHeader = request.getHeader(HEADER_NAME_AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(JWT_TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticatedUser;
        try {
            authenticatedUser = getAuthenticationObject(authorizationHeader);
            onSuccessfulAuthentication(request, response, authenticatedUser);

        } catch (AuthenticationException failedAuth) {
            SecurityContextHolder.clearContext();
            this.getAuthenticationEntryPoint().commence(request, response, failedAuth);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              Authentication authResult) throws IOException {

        SecurityContextHolder.getContext().setAuthentication(authResult);
    }
}
