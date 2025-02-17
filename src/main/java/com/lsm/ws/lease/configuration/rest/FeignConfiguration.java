package com.lsm.ws.lease.configuration.rest;

import com.lsm.ws.lease.configuration.properties.FeignProperties;
import com.lsm.ws.lease.infrastructure.rest.offer.OfferClient;
import com.lsm.ws.lease.infrastructure.rest.user.UserClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(FeignProperties.class)
public class FeignConfiguration {

    private final FeignProperties feignProperties;

    public FeignConfiguration(FeignProperties feignProperties) {
        this.feignProperties = feignProperties;
    }

    @Bean
    UserClient userClient() {
        return new UserClient(restClient(feignProperties.getUser()));
    }

    @Bean
    OfferClient offerClient() {
        return new OfferClient(restClient(feignProperties.getOffer()));
    }

    private WebClient restClient(FeignProperties.MicroserviceProperties properties) {
        return WebClient.builder()
                        .baseUrl(properties.getUrl())
                        .build();
    }
}
