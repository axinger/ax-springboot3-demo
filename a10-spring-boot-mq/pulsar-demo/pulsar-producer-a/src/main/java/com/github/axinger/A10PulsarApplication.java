package com.github.axinger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.pulsar.annotation.EnablePulsar;

@SpringBootApplication
@EnablePulsar
public class A10PulsarApplication {

    public static void main(String[] args) {
        SpringApplication.run(A10PulsarApplication.class, args);
    }

}
