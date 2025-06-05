package com.tickets.reservationservice.entities;

import com.tickets.reservationservice.models.Evenement;
import com.tickets.reservationservice.models.Utilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    private int siege_num;
    private LocalDateTime date_reservation;
    private String statut;

    private int idUtilisateur;
    @Transient
    private Utilisateur utilisateur;

    private int idEvenement;
    @Transient
    private Evenement evenement;
}
