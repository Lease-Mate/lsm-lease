package com.lsm.ws.lease.configuration.exception.unauthorized;

public class JwtAuthenticationException extends UnauthorizedException {

    public JwtAuthenticationException(String message) {
        super(message);
    }
}
