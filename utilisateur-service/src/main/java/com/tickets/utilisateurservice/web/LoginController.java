package com.tickets.utilisateurservice.web;

import com.tickets.utilisateurservice.entities.Utilisateur;
import com.tickets.utilisateurservice.repositories.UtilisateurRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String motPasse,
                        HttpSession session,
                        Model model) {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        for (Utilisateur u : utilisateurs) {
            if (u.getEmail().equals(email) && u.getMotPasse().equals(motPasse)) {
                session.setAttribute("userId", u.getIdUtiliateur());
                return "redirect:http://localhost:9093/evenements?userId=" + u.getIdUtiliateur(); // Redirection vers le service réservation
            }
        }
        model.addAttribute("error", "Email ou mot de passe incorrect");
        return "login";
    }
}

