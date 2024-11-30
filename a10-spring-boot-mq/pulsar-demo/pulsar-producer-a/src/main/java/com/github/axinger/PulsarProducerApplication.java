package com.github.axinger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.pulsar.annotation.EnablePulsar;

@SpringBootApplication
@EnablePulsar
public class PulsarProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PulsarProducerApplication.class, args);
    }

}
