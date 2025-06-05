package com.tickets.reservationservice.web;

import com.tickets.reservationservice.models.Utilisateur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "UTILISATEUR-SERVICE")
public interface UtilisateurOpenFeign {

    @GetMapping("/utilisateurs")
    public List<Utilisateur> getAllUtilisateurs();

    @GetMapping("/utilisateur/{idUtilisateur}")
    public Utilisateur getUtilisateurById(@PathVariable long idUtilisateur);


}
