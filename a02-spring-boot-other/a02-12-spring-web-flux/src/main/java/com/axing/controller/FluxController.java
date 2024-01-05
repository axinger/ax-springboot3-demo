package com.axing.controller;

import com.axing.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/f")
@Slf4j
public class FluxController {

    @Autowired
    private UserService userService;


    /**
     * 服务器发送事件(Server-Sent Events)，简称 SSE 打字效果
     *
     * @param name 参数
     * @return Flux
     */
    @GetMapping(value = "/1", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux(@RequestParam(defaultValue = "") String name) {

        return Flux.range(1, 10)
                .map(val -> name + val)
                .delayElements(Duration.ofSeconds(1))

                ;

    }


    @GetMapping(value = "/2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux2() {
        return Flux.generate(val -> {
            val.next("jim");
            val.complete();
        });
    }

    @GetMapping(value = "/3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux3() {
        return Flux.generate(val -> {
            val.next("jim");
            val.complete();
        });
    }

    //    @GetMapping(value = "/4")
    @GetMapping(value = "/4", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<?> addUser(@RequestParam(defaultValue = "") String name) {
        return Mono.just(1)
                .map(val -> name + val)
                .delayElement(Duration.ofSeconds(1));
    }

    // 查询所有用户 Flux 多个返回值
    @GetMapping("/user")
    public Flux<Object> getAllUser() {
        return userService.getAllUser();
    }
}

