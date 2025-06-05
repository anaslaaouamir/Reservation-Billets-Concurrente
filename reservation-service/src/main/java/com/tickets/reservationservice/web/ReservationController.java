package com.tickets.reservationservice.web;

import com.tickets.reservationservice.entities.Reservation;
import com.tickets.reservationservice.models.Evenement;
import com.tickets.reservationservice.models.Utilisateur;
import com.tickets.reservationservice.repositories.ReservationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class ReservationController {

    ReservationRepository reservationRepository;
    UtilisateurOpenFeign utilisateurOpenFeign;
    EvenementOpenFeign evenementOpenFeign;

    public ReservationController(ReservationRepository reservationRepository, UtilisateurOpenFeign utilisateurOpenFeign, EvenementOpenFeign evenementOpenFeign) {
        this.reservationRepository = reservationRepository;
        this.utilisateurOpenFeign = utilisateurOpenFeign;
        this.evenementOpenFeign=evenementOpenFeign;
    }

    @GetMapping("/reservations")
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
}
