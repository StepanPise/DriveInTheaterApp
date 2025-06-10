package com.example.driveintheater.repository;

import com.example.driveintheater.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    List<Screening> findByMovieId(Long movieId);

    @Query("SELECT s FROM Screening s WHERE DATE(s.screeningTime) = :date ORDER BY s.screeningTime ASC")
    List<Screening> findByScreeningDate(@Param("date") LocalDate date);

    List<Screening> findByScreeningTimeBetween(LocalDateTime from, LocalDateTime to);

    List<Screening> findTop3ByScreeningTimeAfterOrderByScreeningTimeAsc(LocalDateTime now);

}
