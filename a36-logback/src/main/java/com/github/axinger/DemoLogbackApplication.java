package com.github.axinger;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xing
 */
@SpringBootApplication
@Slf4j
public class DemoLogbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoLogbackApplication.class, args);
    }
}
