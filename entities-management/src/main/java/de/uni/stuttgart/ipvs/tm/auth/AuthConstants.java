package de.uni.stuttgart.ipvs.tm.auth;

public class AuthConstants {

    private AuthConstants() {
        throw new IllegalStateException(getClass().getName());
    }

    public static final String JWT_TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_NAME_AUTHORIZATION = "Authorization";
}
