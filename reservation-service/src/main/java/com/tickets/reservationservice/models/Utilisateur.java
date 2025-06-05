package com.tickets.reservationservice.models;

import lombok.*;
import org.springframework.cloud.openfeign.FeignClient;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter


public class Utilisateur {
    private String nom;
    private int idUtilisateur;
    private String email;
}
