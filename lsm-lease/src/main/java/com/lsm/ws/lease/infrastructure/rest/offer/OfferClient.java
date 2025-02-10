package com.lsm.ws.lease.infrastructure.rest.offer;

import com.lsm.ws.lease.infrastructure.rest.offer.dto.OfferDto;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

public class OfferClient {

    private final String GET_OFFER = "/v1/api/offer/internal/{offerId}";

    private final WebClient webClient;

    public OfferClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Optional<OfferDto> getOffer(String offerId) {
        return webClient.get().uri(GET_OFFER, offerId)
                        .retrieve()
                        .bodyToMono(OfferDto.class)
                        .blockOptional();
    }
}
