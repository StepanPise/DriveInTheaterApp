package com.example.driveintheater.repository;
import com.example.driveintheater.model.User; // Přidej tento import

import com.example.driveintheater.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByScreeningId(Long screeningId);

    List<Reservation> findByUserUsername(String username);

}
