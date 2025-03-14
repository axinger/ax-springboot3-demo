package com.github.axinger.consumer;

import cn.hutool.core.util.RandomUtil;
import com.github.axinger.model.Person;
import com.github.axinger.topic.Topic;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MyConsumer1 {

    @SneakyThrows
    @PulsarListener(
//            properties = {  "receiverQueueSize=5000" },
            concurrency = "10",
            subscriptionType = SubscriptionType.Shared,
            topics = Topic.SHARED_TOPIC,
//            schemaType = SchemaType.JSON,
//            schemaType = SchemaType.JSON,
//            schemaType = Schema.JSON(Person.class),
//            schemaType = Schema.JSON(Class<Person>),

            subscriptionName = "my-subscription")
    public void consumeMessage(Person person) {

        int randomInt = RandomUtil.randomInt(100, 5000);
//        log.info("GROUP_2:当前线程池={},监听到消息：user={},randomInt={}", Thread.currentThread().getName(), person.getId(), randomInt);
        log.info("GROUP_2:监听到消息：user={},randomInt={}", person.getId(), randomInt);
        TimeUnit.MILLISECONDS.sleep(randomInt);
    }
}
