package com.lsm.ws.lease.infrastructure.rest.user;

import com.lsm.ws.lease.configuration.exception.unauthorized.UnauthorizedException;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class UserClient {

    private final String AUTH_HEADER = "Authorization";
    private final String VERIFY_URI = "/v1/api/user/internal/auth/verify";

    private final WebClient webClient;

    public UserClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public void verify(String token) {
        webClient.post().uri(VERIFY_URI)
                 .header(AUTH_HEADER, "Bearer " + token)
                 .retrieve()
                 .onStatus(HttpStatusCode::isError, unauthorizedErrorHandler())
                 .toEntity(String.class)
                 .block();
    }

    private Function<ClientResponse, Mono<? extends Throwable>> unauthorizedErrorHandler() {
        return (response) -> response.bodyToMono(Message.class).handle(
                (error, sink) -> sink.error(new UnauthorizedException(error.message))
        );
    }

    static class Message {

        public String message;
    }
}
