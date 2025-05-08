package com.github.axinger.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

@Slf4j
@SuppressWarnings("all")
public class 虚拟线程Tests {

    @Test
    public void test1() {
        Thread.ofVirtual().start(() -> {

            log.info("hello world");
        });
    }

    @Test
    public void test2() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> {
                System.out.println("Running in virtual thread: " + Thread.currentThread());
            });
        }
    }

    private long computeFibonacci(int i) {
        return i < 2 ? i : computeFibonacci(i - 1) + computeFibonacci(i - 2);
    }

    //计算密集型任务（无提升）
    @Test
    public void test3() {
        // 计算斐波那契数列（CPU 密集型）
        Runnable cpuTask = () -> {
            long result = 0;
            for (int i = 0; i < 1_000_000; i++) {
                result += computeFibonacci(i); // 纯计算，无 I/O
            }
        };

// 使用虚拟线程不会比平台线程更快
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        executor.submit(cpuTask); // 速度与平台线程相同
    }

    //IO密集型任务（有提升）
    @Test
    public void test4() {
        // 模拟 HTTP 请求（I/O 密集型）
        Runnable ioTask = () -> {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.example.com/data"))
                    .build();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // 阻塞 I/O
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

// 虚拟线程可高效处理 10,000 个并发请求
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < 10_000; i++) {
            executor.submit(ioTask); // 用少量 OS 线程承载大量虚拟线程
        }
    }

    @Test
    public void test5() {
        //如果任务是 CPU 密集型的，应使用：

        IntStream.range(0, 1_000_000).parallel().forEach(i -> computeFibonacci(i));


        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        pool.submit(() -> computeFibonacci(1));

    }


}
