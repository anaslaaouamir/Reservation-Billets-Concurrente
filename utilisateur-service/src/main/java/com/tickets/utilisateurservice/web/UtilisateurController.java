package com.tickets.utilisateurservice.web;

import com.tickets.utilisateurservice.entities.Utilisateur;
import com.tickets.utilisateurservice.repositories.UtilisateurRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class UtilisateurController {

    UtilisateurRepository  repository;

    UtilisateurController(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/utilisateurs")
    public List<Utilisateur> getAllUtilisateurs() {
        return repository.findAll();
    }

    @GetMapping("/utilisateur/{idUtilisateur}")
    public Utilisateur getUtilisateurById(@PathVariable long idUtilisateur) {
        return repository.findById(idUtilisateur).get();
    }
}
