package com.example.league_service.mapper;

import com.example.league_service.dto.LeagueRequest;
import com.example.league_service.dto.LeagueResponse;
import com.example.league_service.entity.League;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeagueMapper {
    League map(LeagueRequest leagueRequest);
    LeagueResponse map(League league);
    List<LeagueResponse> map(List<League> leagueList);
}
