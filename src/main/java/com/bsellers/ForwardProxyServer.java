package com.bsellers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForwardProxyServer {
    public static void main(final String[] args) {
            SpringApplication.run(ForwardProxyServer.class, args);
    }
}
