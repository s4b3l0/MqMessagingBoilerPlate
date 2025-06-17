package com.example.demo;

import com.example.demo.configuration.MessageConfig;
import com.example.demo.rabbitmq.Receiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(RabbitTemplate rabbitTemplate, Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }


    @Override
    public void run(String... args) throws Exception {
        log.info("Sending message...");

        rabbitTemplate.convertAndSend(
                MessageConfig.topicExchangeName,
                "foo.bar.baz",
                "Hello from RabbitMq");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
