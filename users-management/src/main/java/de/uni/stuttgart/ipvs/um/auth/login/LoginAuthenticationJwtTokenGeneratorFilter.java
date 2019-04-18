package de.uni.stuttgart.ipvs.um.auth.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import de.uni.stuttgart.ipvs.um.auth.AuthConstants;
import de.uni.stuttgart.ipvs.um.form.UserFormControlErrorException;
import de.uni.stuttgart.ipvs.um.form.UserFormModelValidation;
import de.uni.stuttgart.ipvs.um.users.persistence.UserDetailsImpl;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginAuthenticationJwtTokenGeneratorFilter extends UsernamePasswordAuthenticationFilter {

    private final UserFormModelValidation uFMV;
    private final AuthenticationManager authMgr;
    private final JsonWebSignature jws;


    public LoginAuthenticationJwtTokenGeneratorFilter(AuthenticationManager authMgr,
                                                      JsonWebSignature jws,
                                                      UserFormModelValidation uFMV) {
        this.authMgr = authMgr;
        this.jws = jws;
        this.uFMV = uFMV;
        // change default url '/login' to '/api/login'
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
    }

    private static String getPayload(UserDetailsImpl userDetails) {

        JwtClaims claims = new JwtClaims();
        claims.setSubject(userDetails.getId());
        claims.setStringClaim("role", userDetails.getRole());
        claims.setStringClaim("username", userDetails.getUsername());
        claims.setIssuedAtToNow();
        claims.setExpirationTimeMinutesInTheFuture(10000);

        return claims.toJson();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {


        ObjectMapper mapper = new ObjectMapper();

        try {
            UserLoginDTO userLoginDTO = mapper.readValue(request.getInputStream(), UserLoginDTO.class);
            this.validate(userLoginDTO);

            UsernamePasswordAuthenticationToken authRequest = unAuthToken(userLoginDTO);
            userLoginDTO.eraseCredentials();

            return getAuthenticationIfSuccessEraseCredentialsOfPrincipal(authRequest);

        } catch (IOException | UserFormControlErrorException failed) {
            log.error("AUTHENTICATION FAILED", failed);
            throw new BadCredentialsException("username or password is wrong");
        }

    }

    private Authentication getAuthenticationIfSuccessEraseCredentialsOfPrincipal(
            UsernamePasswordAuthenticationToken authRequest) {

        Authentication authenticate = this.authMgr.authenticate(authRequest);
        log.debug("AUTHENTICATION PASSED");

        Object principal = authenticate.getPrincipal();
        ((UserDetailsImpl) principal).eraseCredentials();

        return authenticate;
    }

    private void validate(UserLoginDTO userLoginDTO) {
        this.uFMV.validate(userLoginDTO);
        log.debug("LOGIN USER: FORM VALIDATION PASSED");
    }

    private UsernamePasswordAuthenticationToken unAuthToken(UserLoginDTO userLoginDTO) {
        return new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword());
    }

    private void addBearerHeaderAndSetStatusResponse(HttpServletResponse response) throws JoseException {
        String jwtToken = jws.getCompactSerialization();
        log.debug("JWT TOKEN GENERATED");
        response.addHeader(AuthConstants.HEADER_NAME_AUTHORIZATION, AuthConstants.JWT_TOKEN_PREFIX + jwtToken);
        response.setStatus(204);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authenticationResult) throws IOException {

        SecurityContextHolder.getContext().setAuthentication(authenticationResult);

        Object principal = authenticationResult.getPrincipal();
        UserDetailsImpl user = (UserDetailsImpl) principal;

        jws.setPayload(getPayload(user));

        try {
            addBearerHeaderAndSetStatusResponse(response);
        } catch (JoseException failedJws) {
            log.error("JWT TOKEN ERROR", failedJws);
            throw new IOException("JWT error", failedJws);
        }
    }

}
