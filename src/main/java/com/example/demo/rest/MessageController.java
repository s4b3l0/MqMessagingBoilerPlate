package com.example.demo.rest;

import com.example.demo.configuration.MessageConfig;
import com.example.demo.rabbitmq.Receiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/message")
public class MessageController {
    private Integer num = 1;
    private RabbitTemplate rabbitTemplate;
    private Receiver receiver;

    public MessageController(RabbitTemplate rabbitTemplate,
                             Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    @GetMapping
    private String message() throws InterruptedException {
        rabbitTemplate.convertAndSend(
                MessageConfig.topicExchangeName,
                "foo.bar.baz",
                String.format("messsage sent to another %d", ++num)
        );
        return "sent";
    }


}
