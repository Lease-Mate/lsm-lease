package com.lsm.ws.lease.configuration.properties;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.crypto.SecretKey;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(@NotEmpty
                            String secret) {

    public SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
