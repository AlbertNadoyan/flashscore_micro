package com.example.sport_service.controller;

import com.example.sport_service.dto.SportRequest;
import com.example.sport_service.dto.SportResponse;
import com.example.sport_service.entity.Sport;
import com.example.sport_service.entity.enums.SportType;
import com.example.sport_service.kafka.SportProducer;
import com.example.sport_service.mapper.SportMapper;
import com.example.sport_service.service.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sport")
public class SportController {
    private final SportService sportService;
    private final SportMapper sportMapper;
    private final SportProducer sportProducer;

    @GetMapping
    public List<SportResponse> getAllSport(){
        return sportMapper.map(sportService.getAllSport());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sport> getSportById(@PathVariable("id") int id){
        Optional<Sport> byId = sportService.getSportById(id);
        return byId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PostMapping
//    public ResponseEntity<?> createSport(@RequestBody SportRequest sportRequest){
//        Sport sport = sportService.createSport(sportMapper.map(sportRequest));
//        return ResponseEntity.ok(sport);
//    }

    @PostMapping()
    public ResponseEntity<?> sendSportMessage(@RequestBody SportRequest sportRequest) {
        sportProducer.sendMessage(sportRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sport message sent successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSportById(@PathVariable("id") int id){
        sportService.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}
