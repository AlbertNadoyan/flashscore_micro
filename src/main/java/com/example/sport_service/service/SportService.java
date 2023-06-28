package com.example.sport_service.service;

import com.example.sport_service.entity.Sport;

import java.util.List;
import java.util.Optional;

public interface SportService {
    List<Sport> getAllSport();

    Optional<Sport> getSportById(int id);

    Sport createSport(Sport sport);

    void deleteById(int id);
}
