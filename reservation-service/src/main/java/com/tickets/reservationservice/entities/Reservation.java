package com.tickets.reservationservice.entities;

import com.tickets.reservationservice.models.Evenement;
import com.tickets.reservationservice.models.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Reservation {

    public Reservation(Long userId, Long eventId, int seatNumber) {
        this.idUtilisateur = userId;
        this.idEvenement = eventId;
        this.siegeNum = seatNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    private int siegeNum;
    private LocalDate dateReservation;
    private String statut;

    private Long idUtilisateur;
    @Transient
    private Utilisateur utilisateur;

    private Long idEvenement;
    @Transient
    private Evenement evenement;
}
