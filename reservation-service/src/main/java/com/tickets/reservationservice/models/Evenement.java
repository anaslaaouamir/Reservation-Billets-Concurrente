package com.tickets.reservationservice.models;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter

public class Evenement {

    private int idEvenement;
    private String titre;
    private LocalDateTime dateEvenement;
    private String lieu;
    private int nb_sieges;

}
