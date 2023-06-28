package com.example.team_service.service;

import com.example.team_service.entity.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> getAllTeam();

    Optional<Team> getTeamById(int id);

    Team createTeam(Team map);

    Team updateTeam(int id, Team team);

    void deleteById(int id);
}
