package com.github.axinger.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PulsarConfig {
    /**
     * 生产者,必须先注册topic
     *
     * @return
    //     */
//    @Bean
//    public ProducerFactory producerFactory() {
//        return new ProducerFactory()
//
//                .addProducer(Topic.EXCLUSIVE_TOPIC, Map.class)
//                .addProducer(Topic.FAILOVER_TOPIC, Map.class)
//                .addProducer(Topic.SHARED_TOPIC, Map.class)
//                .addProducer(Topic.KEY_SHARED_TOPIC, Map.class)
//                .addProducer(Topic.DELIVER_AFTER_TOPIC, Map.class)
//                .addProducer(Topic.DELIVER_AT_TOPIC, Map.class)
//
//
//                ;
//    }
}
