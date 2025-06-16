package com.example.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);


    public void receiveMessage(String message) {
        log.info(String.format("Received {}", message));
        latch.countDown();
    }


    public CountDownLatch getLatch() {
        return latch;
    }
}

