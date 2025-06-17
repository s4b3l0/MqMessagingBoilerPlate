package com.example.demo.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);


    public void receiveMessage(String message) {
        CompletableFuture.runAsync(() -> {
            log.info(String.format("Received %s", message));
        });

    }


    public CountDownLatch getLatch() {
        return latch;
    }
}

