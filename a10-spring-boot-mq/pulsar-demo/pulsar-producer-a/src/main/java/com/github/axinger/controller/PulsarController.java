package com.github.axinger.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.fastjson2.JSON;
import com.github.axinger.Person;
import org.apache.pulsar.client.api.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/pulsar")
public class PulsarController {

    @Autowired
    private PulsarTemplate<Person> pulsarTemplate;


    @GetMapping(value = "/sendMessage")
    public void sendMessage() {
        Map<Object, Object> map = new HashMap<>();
        map.put("date", LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
        map.put("data", "发送普通消息");
//        pulsarTemplate.send("bootTopic", "jim");

        Person person = Person.builder()
                .name("jim")
                .build();

        for (int i = 0; i < 20; i++) {
//            pulsarTemplate.send(JSON.toJSONString(person));
//            pulsarTemplate.send("ax-abc",person);
            pulsarTemplate.send("user-topic2",person);
//            pulsarTemplate.newMessage(person)
//                    .withTopic("user-topic2")
//                    .withProducerCustomizer((b) -> b.producerName("user"))
//                    .send();
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
