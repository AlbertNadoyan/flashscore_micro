package com.example.sport_service.kafka;

import com.example.sport_service.dto.SportRequest;
import com.example.sport_service.entity.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SportProducer {
    private static final String TOPIC = "sport_topic";

    private final KafkaTemplate<String, SportRequest> kafkaTemplate;

    @Autowired
    public SportProducer(KafkaTemplate<String, SportRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(SportRequest sport) {
        kafkaTemplate.send(TOPIC, sport);
        System.out.println("Sport message sent: " + sport);
    }
}
