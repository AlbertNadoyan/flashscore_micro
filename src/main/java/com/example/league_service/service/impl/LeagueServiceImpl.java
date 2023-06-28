package com.example.league_service.service.impl;

import com.example.league_service.entity.League;
import com.example.league_service.repository.LeagueRepository;
import com.example.league_service.service.LeagueService;
import com.example.league_service.service.clientService.SportServiceClient;
//import com.example.sport_service.dto.SportResponse;
import com.example.sport_service.entity.Sport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeagueServiceImpl implements LeagueService {
    private final LeagueRepository leagueRepository;
    private final SportServiceClient sportServiceClient;

    @Override
    public List<League> getAllLeague() {
        return leagueRepository.findAll();
    }

    @Override
    public Optional<League> getLeagueById(int id) {
        return leagueRepository.findById(id);
    }

    @Override
    public League createLeague(League league) {
        return leagueRepository.save(league);
    }

    @Override
    public League updateLeague(int id, League league) {
        Optional<League> optionalLeague = leagueRepository.findById(id);
        if (optionalLeague.isPresent()) {
            League existingLeague = optionalLeague.get();
            int sportId = league.getSportId();

            ResponseEntity<Sport> sportResponse = sportServiceClient.getSportById(sportId);
            if (sportResponse.getStatusCode() == HttpStatus.OK) {
                Sport sport = sportResponse.getBody();
                if (sport != null && sport.getId() == sportId) {
                    existingLeague.setName(league.getName());
                    existingLeague.setCountry(league.getCountry());
                    existingLeague.setSportId(league.getSportId());
                    return leagueRepository.save(existingLeague);
                } else {
                    throw new IllegalArgumentException("Sport ID mismatch for the league");
                }
            } else if (sportResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new IllegalArgumentException("Sport not found for the league");
            } else {
                throw new IllegalStateException("Failed to fetch sport details for the league");
            }
        } else {
            throw new IllegalArgumentException("League not found with ID: " + id);
        }
    }

    @Override
    public void deleteById(int id) {
        leagueRepository.deleteById(id);
    }

}
