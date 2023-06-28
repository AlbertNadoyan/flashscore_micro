package com.example.league_service.service.clientService;

import com.example.sport_service.entity.Sport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sport-service")
public interface SportServiceClient {
    @GetMapping("/sport/{id}")
    ResponseEntity<Sport> getSportById(@PathVariable("id") int id);

}
