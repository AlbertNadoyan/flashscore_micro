package com.example.team_service.service.clientService;

import com.example.league_service.entity.League;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "league-service")
public interface LeagueServiceClient {
    @GetMapping("/league/{id}")
    ResponseEntity<League> getLeagueById(@PathVariable("id") int id);
}
