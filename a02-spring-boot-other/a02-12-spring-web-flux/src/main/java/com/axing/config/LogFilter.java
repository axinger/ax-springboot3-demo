package com.axing.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

// 后置过滤器
@Component
public class LogFilter implements WebFilter {
    private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);

    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // [1]
        logger.info("request before, url:{}, statusCode:{}", exchange.getRequest().getURI(), exchange.getResponse().getStatusCode());
        System.out.println("请求处理放行到目标方法之前...");
        return chain.filter(exchange)
                .doOnError(err -> {
                    System.out.println("目标方法异常以后...");
                }) // 目标方法发生异常后做事
                .doFinally(signalType -> {
                    System.out.println("目标方法执行以后...");
                });// 目标方法执行之后

    }
}
