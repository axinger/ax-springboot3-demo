package com.axing.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@SpringBootTest
class MvcApplicationTest {


    @Test
    void test1() {

        RestClient restClient = RestClient.builder()
                .baseUrl("")
                .build();

        String res = restClient
                .get()
                .uri("say1/{content}", "hello")
                .retrieve()
                .body(String.class);

        String res2 = restClient
                .post()
                .uri("say1/{content}", "hello")
                .body(Map.of())
                .retrieve()
                .body(String.class)
        ;


        WebClient.builder()
                .baseUrl("")
                .filter(((request, next) -> {

                    return null;
                }))
                .build();
    }

}
