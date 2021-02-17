package com.microservices.spring.cloud.api.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class MyPreFilter implements GlobalFilter, Ordered {

    final Logger logger = LoggerFactory.getLogger(MyPreFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("My first pre-filter is executed...");

        String exchangePath = exchange.getRequest().getPath().toString();
        logger.info("Request path: ", exchangePath);

        //pass the exchange object to the next filter in chain
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
