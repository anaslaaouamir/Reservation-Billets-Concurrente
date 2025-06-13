package com.tickets.reservationservice.repositories;

import com.tickets.reservationservice.entities.Reservation;
import com.tickets.reservationservice.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByidUtilisateur(long idUtilisateur);
}
