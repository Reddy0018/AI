package com.spring.AI.Kafka;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaStringTemplate;

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(KafkaProducer.class);

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        System.out.println("KafkaProducer initialized");
        this.kafkaStringTemplate = kafkaTemplate;
    }

    public void sendStringMessage(String message){
        logger.info("Producing message: {}", message);
        kafkaStringTemplate.send("Test",  message);
    }

}
