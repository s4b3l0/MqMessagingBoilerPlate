package app.poc.secure.externalconsumerapp.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);


    @RabbitListener(queues = "spring-boot")
    public void receiveMessage(String message) {
        log.info(String.format("Received {}", message));
        latch.countDown();
    }


    public CountDownLatch getLatch() {
        return latch;
    }
}
