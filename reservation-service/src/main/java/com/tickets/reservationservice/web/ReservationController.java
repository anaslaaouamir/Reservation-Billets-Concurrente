package com.tickets.reservationservice.web;

import com.tickets.reservationservice.entities.Reservation;
import com.tickets.reservationservice.entities.Ticket;
import com.tickets.reservationservice.models.Evenement;
import com.tickets.reservationservice.models.Utilisateur;
import com.tickets.reservationservice.repositories.ReservationRepository;
import com.tickets.reservationservice.concurrente.ReservationService;
import com.tickets.reservationservice.repositories.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class ReservationController {

    ReservationRepository reservationRepository;
    UtilisateurOpenFeign utilisateurOpenFeign;
    EvenementOpenFeign evenementOpenFeign;
    ReservationService reservationService;
    TicketRepository ticketRepository;

    public ReservationController(ReservationRepository reservationRepository, UtilisateurOpenFeign utilisateurOpenFeign, EvenementOpenFeign evenementOpenFeign,ReservationService reservationService,TicketRepository ticketRepository) {
        this.reservationRepository = reservationRepository;
        this.utilisateurOpenFeign = utilisateurOpenFeign;
        this.evenementOpenFeign=evenementOpenFeign;
        this.reservationService=reservationService;
        this.ticketRepository=ticketRepository;

    }

    @GetMapping("/getreservations")
    public List<Reservation> getAllEvenements() {
        List<Reservation> reservations = reservationRepository.findAll();

        List<Reservation> ress = new ArrayList<>();

        for (Reservation res : reservations) {
            Utilisateur user = utilisateurOpenFeign.getUtilisateurById(res.getIdUtilisateur());
            res.setUtilisateur(user);

            Evenement evenement=evenementOpenFeign.getEvenementById(res.getIdEvenement());
            res.setEvenement(evenement);

            ress.add(res);
        }
        return ress;
    }

    ////////////////////////// START CHANGING ///////////////////////////////


    @PostMapping("/reserver")
    public ResponseEntity<String> reserver(@RequestParam Long userId,
                                          @RequestParam Long eventId,
                                          @RequestParam int seatNumber) {
        try {
            reservationService.makeReservation(userId, eventId, seatNumber);
            return ResponseEntity.ok("Reservation successful");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/reservations-unlocked")
    public ResponseEntity<String> reserveWithoutLock(@RequestParam Long userId,
                                                     @RequestParam Long eventId,
                                                     @RequestParam int seatNumber) {
        try {
            reservationService.makeReservationWithoutLock(userId, eventId, seatNumber);
            return ResponseEntity.ok("Reservation successful (no lock)");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/tickets1")
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets;
    }

}
