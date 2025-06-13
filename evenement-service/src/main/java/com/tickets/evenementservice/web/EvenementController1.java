package com.tickets.evenementservice.web;

import com.tickets.evenementservice.entities.Evenement;
import com.tickets.evenementservice.repositories.EvenementRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class EvenementController1 {

    EvenementRepository evenementRepository;

    EvenementController1(EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }

    @GetMapping("/evenements-page")
    public String afficherEvenements(Model model) {
        List<Evenement> evenements = evenementRepository.findAll();
        model.addAttribute("evenements", evenements);
        return "evenements";
    }
}
