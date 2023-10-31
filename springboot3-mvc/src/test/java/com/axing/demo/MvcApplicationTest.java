package com.axing.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MvcApplicationTest {


    @Test
    void test1(){
        WebClient.builder()
                .baseUrl("")
                .filter(((request, next) -> {

                    return null;
                }))
                .build();
    }

}
