package com.spring.AI.Kafka;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final KafkaProducer kafkaProducer;
    private final KafkaUserProducer kafkaUserProducer;

    public KafkaController(KafkaProducer kafkaProducer, KafkaUserProducer kafkaUserProducer) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaUserProducer = kafkaUserProducer;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        kafkaProducer.sendStringMessage(message);
        return "Message sent: " + message;
    }

    @PostMapping("/send/user")
    public String sendUserMessage(@RequestBody User user) {
        kafkaUserProducer.sendUserMessage(user);
        return "User message sent: " + user.getName();
    }

}
