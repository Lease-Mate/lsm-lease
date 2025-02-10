package com.lsm.ws.lease.infrastructure.rest.context;

import com.lsm.ws.lease.domain.user.UserRole;
import com.lsm.ws.lease.infrastructure.jwt.JwtType;

public class RequestContext {
    private final JwtType tokenType;
    private final UserRole userRole;
    private final String userId;
    private final String originalToken;

    public RequestContext(
            JwtType tokenType,
            UserRole userRole,
            String userId,
            String originalToken
    ) {
        this.tokenType = tokenType;
        this.userRole = userRole;
        this.userId = userId;
        this.originalToken = originalToken;
    }

    public static RequestContextBuilder builder() {
        return new RequestContextBuilder();
    }

    public JwtType tokenType() {
        return tokenType;
    }

    public UserRole userRole() {
        return userRole;
    }

    public String userId() {
        return userId;
    }

    public String originalToken() {
        return originalToken;
    }
}
