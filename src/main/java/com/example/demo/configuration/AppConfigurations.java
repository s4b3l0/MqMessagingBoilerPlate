package com.example.demo.configuration;
import com.example.rabbitmq.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppConfigurations {

    final String queueName = "spring-boot";


    @Bean()
    Queue queue() {

        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        String topicExchangeName = "spring-boot-exchange";
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("foo.bar.#");
    }

    //not in tutorial
    @Bean
    Receiver receiver() {
        return new Receiver();
    }

    @Bean
    MessageListenerAdapter adapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory factory, MessageListenerAdapter adapter) {

        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setQueueNames(queueName);
        container.setMessageListener(adapter);
        return container;
    }
}
