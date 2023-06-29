package com.example.sport_service.kafka;

import com.example.sport_service.dto.SportRequest;
import com.example.sport_service.entity.Sport;
import com.example.sport_service.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class SportConsumer {
    private final SportRepository sportRepository;
    @KafkaListener(topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void receiveMessage(SportRequest sport) {
        Sport newSport = new Sport();
        newSport.setSportType(sport.getSportType());
        sportRepository.save(newSport);

        System.out.println("Received Sport message: " + sport);
    }
}
