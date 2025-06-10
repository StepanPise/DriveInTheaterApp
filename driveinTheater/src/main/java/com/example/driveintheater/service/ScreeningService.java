package com.example.driveintheater.service;

import com.example.driveintheater.model.Screening;
import com.example.driveintheater.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    @Autowired
    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public Screening getScreeningById(Long id) {
        return screeningRepository.findById(id).orElse(null);
    }

    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }

    public List<Screening> getScreeningsBetween(LocalDateTime from, LocalDateTime to) {
        return screeningRepository.findByScreeningTimeBetween(from, to);
    }

}
