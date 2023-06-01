package com.bsellers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class ForwardProxyController {
    private final ForwardService service;
    @GetMapping("**")
    public Mono<ResponseEntity<byte[]>> handleRequest(final ServerHttpRequest request) {
        final var path = request.getPath();
        return service.forward(String.valueOf(path));
    }
}
