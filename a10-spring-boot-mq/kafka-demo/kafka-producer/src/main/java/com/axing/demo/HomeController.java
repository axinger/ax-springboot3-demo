package com.axing.demo;

import com.axing.demo.api.Topic;
import com.axing.demo.model.ProducerUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@RestController
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private KafkaTemplate<String, ProducerUser> kafkaTemplate;


    /***
     * 发送消息体为基本类型的消息
     */
    @GetMapping("/sendSimple")
    public Object sendSimple() {

        ProducerUser ProducerUser = new ProducerUser();
        ProducerUser.setLastName("jim");
        ProducerUser.setBirthday(LocalDateTime.now());
        CompletableFuture<SendResult<String, ProducerUser>> send = kafkaTemplate.send(Topic.USER, ProducerUser);

        send.thenAccept(val -> {
                    log.info("发送消息成功,{}", val.toString());
                })
                .exceptionally(exception -> {
                    log.info("发送消息失败,{}", exception.getMessage());
                    return null;
                });

//        send.addCallback(new ListenableFutureCallback<>() {
//
//            @Override
//            public void onFailure(@NonNull Throwable throwable) {
//                log.info("发送消息失败,{}", throwable.getMessage());
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, ProducerUser> sendResult) {
//                log.info("发送消息成功,{}", sendResult.toString());
//            }
//        });
        SendResult<String, ProducerUser> join = send.join();
        ProducerUser value = join.getProducerRecord().value();
        log.info("发送完成={}", value);
        return value;
    }

    /***
     * 多消费者组、组中多消费者对同一主题的消费情况
     */
    @GetMapping("/sendGroup")
    public String sendGroup() {
        for (int i = 0; i < 4; i++) {
            // 第二个参数指定分区，第三个参数指定消息键 分区优先
            int i2 = i % 4;
            log.info("partition = {}", i2);
            ProducerUser ProducerUser = new ProducerUser();
            ProducerUser.setLastName("jim");
            ProducerUser.setBirthday(LocalDateTime.now());
            CompletableFuture<SendResult<String, ProducerUser>> send = kafkaTemplate.send(Topic.GROUP, i % 4, "key", ProducerUser);

            // future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            //     @Override
            //     public void onFailure(Throwable throwable) {
            //         log.info("发送消息失败,{}" + throwable.getMessage());
            //     }
            //
            //     @Override
            //     public void onSuccess(SendResult<String, Object> sendResult) {
            //         log.info("发送消息成功,{}", sendResult.toString());
            //     }
            // });

        }
        return "group success";
    }


}
