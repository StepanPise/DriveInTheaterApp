package com.example.driveintheater.service;

import com.example.driveintheater.model.Reservation;
import com.example.driveintheater.repository.ReservationRepository;
import com.example.driveintheater.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository,
                              UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public List<Reservation> getUserReservations(String username) {
        return reservationRepository.findByUserUsername(username);
    }

    public boolean deleteReservation(Long reservationId, String username) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null || !reservation.getUser().getUsername().equals(username)) {
            return false;
        }
        reservationRepository.deleteById(reservationId);
        return true;
    }

    @Transactional
    public void deleteAllReservations(String username) {
        List<Reservation> reservations = reservationRepository.findByUserUsername(username);
        reservationRepository.deleteAll(reservations);
    }
}
