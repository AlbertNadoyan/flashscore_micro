package com.example.team_service.mapper;

import com.example.team_service.dto.TeamRequest;
import com.example.team_service.dto.TeamResponse;
import com.example.team_service.entity.Team;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    Team map(TeamRequest teamRequest);

    TeamResponse map(Team team);

    List<TeamResponse> map(List<Team> teamList);
}
