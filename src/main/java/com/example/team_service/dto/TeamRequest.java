package com.example.team_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamRequest {
    private String name;
    private String history;
    private int sportId;
    private int leagueId;

}
