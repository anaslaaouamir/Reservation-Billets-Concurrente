package com.tickets.reservationservice.repositories;

import com.tickets.reservationservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
