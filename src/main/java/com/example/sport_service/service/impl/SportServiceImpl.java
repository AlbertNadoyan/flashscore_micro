package com.example.sport_service.service.impl;

import com.example.sport_service.entity.Sport;
import com.example.sport_service.repository.SportRepository;
import com.example.sport_service.service.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SportServiceImpl implements SportService {
    private final SportRepository sportRepository;

    public List<Sport> getAllSport(){
        return sportRepository.findAll();
    }

    @Override
    public Optional<Sport> getSportById(int id) {
        return sportRepository.findById(id);
    }

    @Override
    public Sport createSport(Sport sport) {
        if(sport == null){
            throw new RuntimeException("Sport is null");
        }
        return sportRepository.save(sport);
    }

    @Override
    public void deleteById(int id) {
        sportRepository.deleteById(id);
    }
}
