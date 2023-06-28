package com.example.league_service.controller;

import com.example.league_service.dto.LeagueRequest;
import com.example.league_service.dto.LeagueResponse;
import com.example.league_service.entity.League;
import com.example.league_service.mapper.LeagueMapper;
import com.example.league_service.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/league")
public class LeagueController {
    private final LeagueService leagueService;
    private final LeagueMapper leagueMapper;

    @GetMapping
    public List<LeagueResponse> getAllLeague(){
        return leagueMapper.map(leagueService.getAllLeague());
    }

    @GetMapping("/{id}")
    public ResponseEntity<League> getLeagueById(@PathVariable("id") int id){
        Optional<League> byId = leagueService.getLeagueById(id);
        return byId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createLeague(@RequestBody LeagueRequest leagueRequest){
        League league = leagueService.createLeague(leagueMapper.map(leagueRequest));
        return ResponseEntity.ok(league);
    }

    @PutMapping("/{id}")
    public ResponseEntity<League> updateLeague(@PathVariable("id") int id, @RequestBody League league){
        try {
            League updatedLeague = leagueService.updateLeague(id, league);
            return ResponseEntity.ok(updatedLeague);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLeagueById(@PathVariable("id") int id){
        leagueService.deleteById(id);
        return ResponseEntity.notFound().build();
    }

}
