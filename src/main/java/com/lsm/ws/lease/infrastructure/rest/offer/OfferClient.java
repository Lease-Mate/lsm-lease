package com.lsm.ws.lease.infrastructure.rest.offer;

import com.lsm.ws.lease.infrastructure.rest.offer.dto.OfferDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Function;

public class OfferClient {

    private final String GET_OFFER = "/v1/api/offer/internal/{offerId}";

    private final WebClient webClient;

    public OfferClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Optional<OfferDto> getOffer(String offerId) {
        return webClient.get().uri(GET_OFFER, offerId)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, defaultErrorHandler())
                        .bodyToMono(OfferDto.class)
                        .blockOptional();
    }

    private Function<ClientResponse, Mono<? extends Throwable>> defaultErrorHandler() {
        return response -> {
            if (response.statusCode().value() == 404) {
                return Mono.empty();
            }
            return response.bodyToMono(Message.class).handle(
                    (error, sink) -> sink.error(new RuntimeException(error.message)));
        };
    }

    static class Message {

        public String message;
    }
}
