package com.github.axinger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class ThreadController {

    @GetMapping("/test1")
    public String test1() {
        return "Current thread: " + Thread.currentThread() +
                "\nIs virtual? " + Thread.currentThread().isVirtual();
    }

    @GetMapping("/test2")
    public String test2() {

        return CompletableFuture.completedFuture(
                "Async thread: " + Thread.currentThread() +
                        "\nIs virtual? " + Thread.currentThread().isVirtual()
        ).join();

    }
}
