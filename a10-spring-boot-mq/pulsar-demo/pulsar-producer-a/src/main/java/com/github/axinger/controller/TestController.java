package com.github.axinger.controller;

import com.github.axinger.model.Person;
import com.github.axinger.topic.Topic;
import org.apache.pulsar.client.api.MessageId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


@RestController
public class TestController {

    @Autowired
    private PulsarTemplate<Person> pulsarTemplate;


    @GetMapping("/more")
    public void sendMessage() {


        for (int i = 0; i < 20; i++) {
            Person person = new Person();
            person.setId(i);
            person.setName("jim_" + i);
            person.setAge(i);

            CompletableFuture<MessageId> completableFuture = pulsarTemplate.sendAsync(Topic.SHARED_TOPIC, person);
            // 通过异步回调得知消息发送成功与否
            completableFuture.whenComplete(((messageId, throwable) -> {
                if (null != throwable) {
                    System.out.println("delivery failed, value: " + person);
                    // 此处可以添加延时重试的逻辑
                } else {
                    System.out.println("delivered msg " + messageId + ", value:" + person);
                }
            }));

        }
    }


//    @GetMapping(value = "/deliverAfter")
//    @ResponseBody
//    public void deliverAfter() {
//        // Map map = new HashMap<>();
//        // map.put("date", LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
//        // map.put("data", "发送普通消息");
//        // pulsarProducer.send(map);
//        pulsarProducer.deliverAfter();
//
//    }
}
