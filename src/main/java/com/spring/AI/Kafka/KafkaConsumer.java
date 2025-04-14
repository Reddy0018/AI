package com.spring.AI.Kafka;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

//    @KafkaListener(topics = "Test", groupId = "ai" )
//    public ResponseEntity<String> consumeTopicTest(String message) {
//        System.out.println("Consumed message: " + message);
//        return ResponseEntity.ok("Consumed message: " + message);
//    }

    @KafkaListener(topics = "Test", groupId = "ai")
    public ResponseEntity<String> consumeUserTopicTest(User user) {
        System.out.println("Consumed user message: " + user);
        return ResponseEntity.ok("Consumed user message: " + user);
    }

}
