package com.example.sport_service.dto;

import com.example.sport_service.entity.enums.SportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportRequest {
    private SportType sportType;

}
