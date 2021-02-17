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
public class MyPostFilter implements GlobalFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(MyPostFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //the main difference to prefilters is how the value is returned
        //after all other filters run, code in THEN of the post-filter is executed !
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            logger.info("My last Post-filter executed...");
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
