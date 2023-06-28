package com.example.league_service.service;

import com.example.league_service.entity.League;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface LeagueService {
    List<League> getAllLeague();

    Optional<League> getLeagueById(int id);

    League createLeague(League map);

    League updateLeague(int id, League league);

//    ResponseEntity<String> deleteById(int id);
    void deleteById(int id);
//    List<SportResponse> get();
}
