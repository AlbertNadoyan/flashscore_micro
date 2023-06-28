package com.example.sport_service.mapper;

import com.example.sport_service.dto.SportRequest;
import com.example.sport_service.dto.SportResponse;
import com.example.sport_service.entity.Sport;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SportMapper {
    Sport map(SportRequest sportRequest);

    SportResponse map(Sport sport);

    List<SportResponse> map(List<Sport> sportList);

}
