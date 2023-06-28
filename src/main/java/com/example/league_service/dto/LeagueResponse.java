package com.example.league_service.dto;

import com.example.league_service.entity.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeagueResponse {
    private int id;
    private String name;
    private Country country;
    private int sportId;
}
