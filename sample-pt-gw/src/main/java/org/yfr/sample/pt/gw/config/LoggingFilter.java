package org.yfr.sample.pt.gw.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("{} {} {} {}", exchange.getRequest().getRemoteAddress().toString(),
                printHeaders(exchange.getRequest().getHeaders()),
                exchange.getRequest().getMethodValue(),
                exchange.getRequest().getURI());

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1000;
    }

    private String printHeaders(HttpHeaders httpHeaders) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : httpHeaders.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue().toString()).append(",");
        }
        return builder.toString();
    }

}
