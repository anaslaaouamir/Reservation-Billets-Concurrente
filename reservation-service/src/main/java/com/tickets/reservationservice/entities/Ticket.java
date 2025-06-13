package com.tickets.reservationservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;
    private Long idUtilisateur;
    private Long idReservation;
    private String NomUtilisateur;
    private int siegeNum;
    private String titreEvenement;
    private LocalDate dateEvenement;
    private String lieu;
}
