package com.lsm.ws.lease.infrastructure.rest.offer;

import com.lsm.ws.lease.domain.offer.Offer;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

public class OfferClient {

    private final String GET_OFFER = "/v1/api/offer/internal/{offerId}";

    private final WebClient webClient;

    public OfferClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Optional<Offer> getOffer(String offerId) {
        return webClient.get().uri(GET_OFFER, offerId)
                        .retrieve()
                        .bodyToMono(Offer.class)
                        .blockOptional();
    }
}
