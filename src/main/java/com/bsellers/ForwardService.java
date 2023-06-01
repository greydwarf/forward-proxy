package com.bsellers;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ForwardService {
    private final WebClient largeWebClient;

    public Mono<ResponseEntity<byte[]>> forward(final String path) {
        return largeWebClient
                .get()
                .uri(path)
                .exchangeToMono(this::toByteArrayResource)
                .map(this::onComplete);

    }

    private Mono<ResponseEntity<ByteArrayResource>> toByteArrayResource(ClientResponse response) {
        return response.toEntity(ByteArrayResource.class);

    }

    private ResponseEntity<byte[]> onComplete(ResponseEntity<ByteArrayResource> source) {
        return new ResponseEntity<>(source.getBody().getByteArray(), source.getHeaders(), source.getStatusCode());
    }

}
