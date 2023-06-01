package com.bsellers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientBean {
    @Bean
    public WebClient largeWebClient(WebClient.Builder builder,
                                   @Value("forward.proxy.url-prefix") String prefix) {
        final int size = 1024 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        return builder
                .exchangeStrategies(strategies)
                .baseUrl(prefix)
                .build();
    }
}
