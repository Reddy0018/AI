package com.spring.AI.Kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaUserProducer {

    private final KafkaTemplate<String, User> kafkaUserTemplate;

    public KafkaUserProducer(KafkaTemplate<String, User> kafkaUserTemplate) {
        this.kafkaUserTemplate = kafkaUserTemplate;
    }

    public void sendUserMessage(User user) {
        System.out.println("Producing user message: " + user);
        /**Message<User> message = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, "Test")
                .build();
         kafkaUserTemplate.send(message); */
        kafkaUserTemplate.send("Test",user);
    }
}
