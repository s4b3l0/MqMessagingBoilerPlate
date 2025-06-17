package app.poc.secure.externalconsumerapp;

import app.poc.secure.externalconsumerapp.configuration.MessageConfig;
import app.poc.secure.externalconsumerapp.rabbitmq.Receiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ExternalConsumerAppApplication {

	Receiver receiver;

	public ExternalConsumerAppApplication(Receiver receiver
	) throws InterruptedException {
		this.receiver = receiver;
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
	}

	public static void main(String[] args) {
		SpringApplication.run(ExternalConsumerAppApplication.class, args);
	}

}
