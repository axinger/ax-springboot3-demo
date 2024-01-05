package com.axing.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

// 前置过滤器，在调用逻辑方法前的检查请求token
@Component
public class TokenCheckFilter implements WebFilter {
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if(!exchange.getRequest().getHeaders().containsKey("token")) {
            ServerHttpResponse response =  exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

            Map<String,Object> map = new HashMap<>();
            map.put("","");
            return response.writeWith(Mono.just(response.bufferFactory().wrap(map.toString().getBytes())));
        } else {
            exchange.getAttributes().put("auth", "true");
            return chain.filter(exchange);
        }
    }
}
