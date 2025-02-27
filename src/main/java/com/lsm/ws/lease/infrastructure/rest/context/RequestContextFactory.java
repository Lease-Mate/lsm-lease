package com.lsm.ws.lease.infrastructure.rest.context;

import com.lsm.ws.lease.configuration.exception.unauthorized.JwtAuthenticationException;
import com.lsm.ws.lease.domain.user.UserRole;
import com.lsm.ws.lease.infrastructure.jwt.JwtClaims;
import com.lsm.ws.lease.infrastructure.jwt.JwtExtractor;
import com.lsm.ws.lease.infrastructure.jwt.JwtType;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class RequestContextFactory {
    private final JwtExtractor jwtExtractor;
    private final HttpServletRequest request;

    public RequestContextFactory(JwtExtractor jwtExtractor, HttpServletRequest request) {
        this.jwtExtractor = jwtExtractor;
        this.request = request;
    }

    @RequestScope
    @Bean
    RequestContext requestContext() {
        if (!jwtExtractor.isAuthHeaderPresent(request)) {
            return RequestContext.builder().build();
        }

        var jwt = jwtExtractor.extractJwtFromRequest(request);
        var claims = jwtExtractor.validateTokenAndExtractClaims(jwt);
        var tokenType = jwtExtractor.extractClaim(JwtClaims.TYPE, claims)
                                    .map(JwtType::valueOf)
                                    .orElseThrow(() -> new JwtAuthenticationException(
                                            "Error during extracting token type"));
        if (tokenType == JwtType.REFRESH) {
            return requestContextForRefreshToken(claims);
        }

        var role = jwtExtractor.extractClaim(JwtClaims.ROLE, claims)
                               .map(UserRole::valueOf)
                               .orElseThrow(() -> new JwtAuthenticationException(
                                       "Error during extracting user role"));
        var userId = jwtExtractor.extractClaim(JwtClaims.USER_ID, claims)
                                 .orElseThrow(() -> new JwtAuthenticationException(
                                         "Error during extracting user id"
                                 ));
        return RequestContext.builder()
                             .withTokenType(tokenType)
                             .withUserRole(role)
                             .withUserId(userId)
                             .withOriginalToken(jwt)
                             .build();
    }

    private RequestContext requestContextForRefreshToken(Claims claims) {
        var originalToken = jwtExtractor.extractClaim(JwtClaims.ORIGINAL_TOKEN, claims)
                                        .orElseThrow(() -> new JwtAuthenticationException(
                                                "Error during extracting original token"));
        return RequestContext.builder()
                             .withTokenType(JwtType.REFRESH)
                             .withOriginalToken(originalToken)
                             .build();
    }
}
