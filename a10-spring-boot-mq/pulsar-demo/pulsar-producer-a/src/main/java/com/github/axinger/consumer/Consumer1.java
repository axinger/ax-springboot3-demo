package com.github.axinger.consumer;

import com.github.axinger.Person;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.api.SubscriptionType;
import org.apache.pulsar.common.schema.SchemaType;
import org.springframework.messaging.Message;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class Consumer1 {

    @SneakyThrows
    @PulsarListener(
//            properties = {  "receiverQueueSize=5000" },
            concurrency = "10",
          subscriptionType = SubscriptionType.Shared,
            topics ={ "user-topic2"},
            schemaType = SchemaType.JSON,
//            schemaType = SchemaType.JSON,
//            schemaType = Schema.JSON(Person.class),
//            schemaType = Schema.JSON(Class<Person>),

            subscriptionName = "my-subscription")
    public void consumeMessage(Person message) {

        TimeUnit.SECONDS.sleep(2);
        log.info("接收到消息: 线程池={}，{}", Thread.currentThread().getName(),message);
    }
}
