package com.tickets.reservationservice.repositories;

import com.tickets.reservationservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByIdEvenementAndSiegeNum(Long idEvenement, int siegeNum);

    List<Reservation> findByidEvenement(Long idEvenement);
}
