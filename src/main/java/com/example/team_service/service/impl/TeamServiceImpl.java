package com.example.team_service.service.impl;

import com.example.league_service.entity.League;
import com.example.sport_service.entity.Sport;
import com.example.team_service.entity.Team;
import com.example.team_service.repository.TeamRepository;
import com.example.team_service.service.TeamService;
import com.example.team_service.service.clientService.LeagueServiceClient;
import com.example.team_service.service.clientService.SportServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final SportServiceClient sportServiceClient;
    private final LeagueServiceClient leagueServiceClient;

    @Override
    public List<Team> getAllTeam() {
        List<Team> teams =  teamRepository.findAll();
        for (Team team : teams) {
            int sportId = team.getSportId();
            int leagueId = team.getLeagueId();
            ResponseEntity<Sport> sportResponseEntity = sportServiceClient.getSportById(sportId);
            ResponseEntity<League> leagueResponseEntity = leagueServiceClient.getLeagueById(leagueId);
            if(sportResponseEntity.getStatusCode() == HttpStatus.OK && leagueResponseEntity.getStatusCode() == HttpStatus.OK){
                Sport sport = sportResponseEntity.getBody();
                League league = leagueResponseEntity.getBody();
                if(sport != null && league != null){
                    team.setSportId(sport.getId());
                    team.setLeagueId(league.getId());
                }
            }
        }
        return teams;
    }

    @Override
    public Optional<Team> getTeamById(int id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team createTeam(Team team) {
        Team teamObj = new Team();
        teamObj.setName(team.getName());
        teamObj.setHistory(team.getHistory());

        int sportId = team.getSportId();
        ResponseEntity<Sport> sportResponseEntity = sportServiceClient.getSportById(sportId);
        if(sportResponseEntity.getStatusCode() == HttpStatus.OK){
            Sport sport = sportResponseEntity.getBody();
            teamObj.setSportId(sport.getId());
        }else {
            throw new RuntimeException("Failed to retrieve sport details");
        }

        int leagueId = team.getLeagueId();
        ResponseEntity<League> leagueResponseEntity = leagueServiceClient.getLeagueById(leagueId);
        if(leagueResponseEntity.getStatusCode() == HttpStatus.OK){
            League league = leagueResponseEntity.getBody();
            teamObj.setLeagueId(league.getId());
        }else {
            throw new RuntimeException("Failed to retrieve league details");
        }
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(int id, Team team) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if(optionalTeam.isPresent()){
            Team existingTeam = optionalTeam.get();
            int sportId = team.getSportId();
            int leagueId = team.getLeagueId();
            ResponseEntity<Sport> sportResponseEntity = sportServiceClient.getSportById(sportId);
            ResponseEntity<League> leagueResponseEntity = leagueServiceClient.getLeagueById(leagueId);
            if(sportResponseEntity.getStatusCode() == HttpStatus.OK && leagueResponseEntity.getStatusCode() == HttpStatus.OK){
                Sport sport = sportResponseEntity.getBody();
                League league = leagueResponseEntity.getBody();
                if(sport != null && sport.getId() == sportId && league != null && league.getId() == leagueId){
                    existingTeam.setName(team.getName());
                    existingTeam.setHistory(team.getHistory());
                    existingTeam.setSportId(team.getSportId());
                    existingTeam.setLeagueId(team.getLeagueId());
                    return teamRepository.save(existingTeam);
                }else {
                    throw new IllegalArgumentException("Sport or League ID mismatch for the team");
                }
            } else if (sportResponseEntity.getStatusCode() == HttpStatus.NOT_FOUND && leagueResponseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new IllegalArgumentException("Sport or League not found for the team");
            }else {
                throw new IllegalStateException("Failed to fetch sport or league details for the team");
            }
        }else {
            throw new IllegalArgumentException("Team not found with ID: " + id);
        }
    }

    @Override
    public void deleteById(int id) {
        teamRepository.deleteById(id);
    }
}
