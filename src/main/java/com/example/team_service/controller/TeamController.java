package com.example.team_service.controller;

import com.example.team_service.dto.TeamRequest;
import com.example.team_service.dto.TeamResponse;
import com.example.team_service.entity.Team;
import com.example.team_service.mapper.TeamMapper;
import com.example.team_service.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @GetMapping
    public List<TeamResponse> getAllTeam(){
        return teamMapper.map(teamService.getAllTeam());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable("id") int id){
        Optional<Team> byId = teamService.getTeamById(id);
        return byId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody TeamRequest teamRequest){
        Team team = teamService.createTeam(teamMapper.map(teamRequest));
        return ResponseEntity.ok(team);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable("id") int id, @RequestBody Team team){
        try {
            Team updateTeam = teamService.updateTeam(id, team);
            return ResponseEntity.ok(updateTeam);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeamById(@PathVariable("id") int id){
        teamService.deleteById(id);
        return ResponseEntity.notFound().build();
    }

}
