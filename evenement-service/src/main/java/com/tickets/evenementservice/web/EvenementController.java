package com.tickets.evenementservice.web;

import com.tickets.evenementservice.entities.Evenement;
import com.tickets.evenementservice.repositories.EvenementRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class EvenementController {

    EvenementRepository evenementRepository;

    EvenementController(EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }

    @GetMapping("/evenements")
    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }

    @GetMapping("/evenement/{idEvenement}")
    public Evenement getEvenementById(@PathVariable long idEvenement) {
        return evenementRepository.findById(idEvenement).get();
    }
}
