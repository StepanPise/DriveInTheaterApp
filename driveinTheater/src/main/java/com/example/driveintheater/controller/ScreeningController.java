package com.example.driveintheater.controller;

import com.example.driveintheater.model.Reservation;
import com.example.driveintheater.model.Screening;
import com.example.driveintheater.repository.ScreeningRepository;
import com.example.driveintheater.service.ScreeningService;
import com.example.driveintheater.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ScreeningController {

    private final ScreeningService screeningService;
    private final ReservationRepository reservationRepository;
    private final ScreeningRepository screeningRepository;

    @Autowired
    public ScreeningController(ScreeningService screeningService, ReservationRepository reservationRepository, ScreeningRepository screeningRepository) {
        this.screeningService = screeningService;
        this.reservationRepository = reservationRepository;
        this.screeningRepository = screeningRepository;
    }

    @GetMapping("/screenings/{id}/seats")
    public String showSeats(@PathVariable Long id, Model model) {
        Screening screening = screeningRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid screening ID"));

        List<String> reservedSpots = reservationRepository.findByScreeningId(id)
                .stream()
                .map(Reservation::getParkingSpot)
                .toList();

        model.addAttribute("screening", screening);
        model.addAttribute("reservations", reservedSpots);

        return "seats";
    }


}
