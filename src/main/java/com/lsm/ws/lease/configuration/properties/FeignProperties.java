package com.lsm.ws.lease.configuration.properties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Validated
@ConfigurationProperties(prefix = "feign")
public class FeignProperties {

    @NotNull
    private MicroserviceProperties user;

    @NotNull
    private MicroserviceProperties offer;

    public @NotNull MicroserviceProperties getUser() {
        return user;
    }

    public void setUser(@NotNull MicroserviceProperties user) {
        this.user = user;
    }

    public @NotNull MicroserviceProperties getOffer() {
        return offer;
    }

    public void setOffer(@NotNull MicroserviceProperties offer) {
        this.offer = offer;
    }

    public static class MicroserviceProperties {

        @NotEmpty
        private String url;

        @NotNull
        private Duration connectTimeout;

        @NotNull
        private Duration requestTimeout;

        public @NotEmpty String getUrl() {
            return url;
        }

        public void setUrl(@NotEmpty String url) {
            this.url = url;
        }

        public @NotNull Duration getConnectTimeout() {
            return connectTimeout;
        }

        public void setConnectTimeout(@NotNull Duration connectTimeout) {
            this.connectTimeout = connectTimeout;
        }

        public @NotNull Duration getRequestTimeout() {
            return requestTimeout;
        }

        public void setRequestTimeout(@NotNull Duration requestTimeout) {
            this.requestTimeout = requestTimeout;
        }
    }
}
