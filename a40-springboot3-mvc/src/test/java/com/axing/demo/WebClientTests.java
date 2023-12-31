package com.axing.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
public class WebClientTests {

    //    reactive 库
    @Test
    void test1() {
        WebClient.builder()
                .baseUrl("")
                .filter(((request, next) -> {

                    return null;
                }))
                .build();
    }
}
