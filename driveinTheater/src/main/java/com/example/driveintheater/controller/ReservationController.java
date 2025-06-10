package com.example.driveintheater.controller;

import com.example.driveintheater.model.Reservation;
import com.example.driveintheater.model.Screening;
import com.example.driveintheater.model.User;
import com.example.driveintheater.repository.ReservationRepository;
import com.example.driveintheater.repository.ScreeningRepository;
import com.example.driveintheater.repository.UserRepository;
import com.example.driveintheater.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final ScreeningRepository screeningRepository;
    private final UserRepository userRepository;
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository,
                                 ScreeningRepository screeningRepository,
                                 UserRepository userRepository,
                                 ReservationService reservationService) {
        this.reservationRepository = reservationRepository;
        this.screeningRepository = screeningRepository;
        this.userRepository = userRepository;
        this.reservationService = reservationService;
    }

    @PostMapping("/reservations")
    public String createReservation(@RequestParam Long screeningId,
                                    @RequestParam String parkingSpot,
                                    Model model,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Screening screening = screeningRepository.findById(screeningId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid screening ID"));

        Reservation reservation = new Reservation();
        reservation.setScreening(screening);
        reservation.setUser(user);
        reservation.setParkingSpot(parkingSpot);
        reservation.setReservationTime(LocalDateTime.now());

        reservationRepository.save(reservation);

        model.addAttribute("reservation", reservation);
        model.addAttribute("screening", screening);
        model.addAttribute("user", user);

        return "reservationConfirmation";
    }

    @GetMapping("/userProfile")
    public String getUserReservations(Model model,
                                      @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        Optional<User> optionalUser = userRepository.findByUsername(userDetails.getUsername());

        if (optionalUser.isEmpty()) {
            return "redirect:/login";
        }

        User user = optionalUser.get();

        List<Reservation> userReservations = reservationRepository.findByUserUsername(user.getUsername());

        model.addAttribute("reservations", userReservations);
        model.addAttribute("user", user);

        return "userProfile";
    }


    @PostMapping("/reservations/delete")
    public String deleteReservation(@RequestParam Long reservationId,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElse(null);

        if (reservation == null) {
            return "redirect:/userProfile?error=reservationNotFound";
        }

        if (!reservation.getUser().getUsername().equals(userDetails.getUsername())) {
            return "redirect:/userProfile?unauthorized";
        }

        reservationRepository.deleteById(reservationId);
        return "redirect:/userProfile";
    }

    @PostMapping("/reservations/deleteAll")
    public String deleteAllReservations(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        reservationService.deleteAllReservations(userDetails.getUsername());

        return "redirect:/userProfile";
    }


}

