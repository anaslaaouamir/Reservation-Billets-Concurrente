package com.tickets.reservationservice.web;

import com.tickets.reservationservice.concurrente.ReservationService;
import com.tickets.reservationservice.entities.Reservation;
import com.tickets.reservationservice.entities.Ticket;
import com.tickets.reservationservice.models.Evenement;
import com.tickets.reservationservice.models.Utilisateur;
import com.tickets.reservationservice.repositories.ReservationRepository;
import com.tickets.reservationservice.repositories.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReservationController2 {

    EvenementOpenFeign evenementOpenFeign;
    ReservationService reservationService;
    TicketRepository ticketRepository;
    UtilisateurOpenFeign utilisateurOpenFeign;
    ReservationRepository reservationRepository;

    public ReservationController2(EvenementOpenFeign evenementOpenFeign,ReservationService reservationService,TicketRepository ticketRepository,UtilisateurOpenFeign utilisateurOpenFeign,ReservationRepository reservationRepository) {
        this.evenementOpenFeign = evenementOpenFeign;
        this.reservationService = reservationService;
        this.ticketRepository = ticketRepository;
        this.utilisateurOpenFeign = utilisateurOpenFeign;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/evenements")
    public String afficherEvenements(@RequestParam("userId") Long userId, Model model) {
        List<Evenement> evenements = evenementOpenFeign.getAllEvenements();
        model.addAttribute("evenements", evenements);
        model.addAttribute("userId", userId); // Add to the model
        return "evenements";
    }






    @PostMapping("/reserve")
    public String reserve(@RequestParam Long userId,
                          @RequestParam Long eventId,
                          @RequestParam int seatNumber,
                          Model model) {
        try {
            reservationService.makeReservation(userId, eventId, seatNumber);
            //reservationService.makeReservationWithoutLock(userId,eventId,seatNumber);
            model.addAttribute("success", "Réservation réussie !");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        // Reload the list of events and keep the userId in the model
        List<Evenement> evenements = evenementOpenFeign.getAllEvenements();
        model.addAttribute("evenements", evenements);
        model.addAttribute("userId", userId);
        return "evenements"; // Return the same page with a message
    }

    @GetMapping("/tickets")
    public String showUserTickets(@RequestParam Long userId, Model model) {
        Utilisateur utilisateur = utilisateurOpenFeign.getUtilisateurById(userId);
        List<Ticket> tickets = ticketRepository.findByidUtilisateur(utilisateur.getIdUtilisateur());
        model.addAttribute("tickets", tickets);
        model.addAttribute("user", utilisateur);
        return "tickets"; // A simple HTML page that shows the list
    }



    //////////////////// ADMIN /////////////////////////

    @GetMapping("/evenements_admin")
    public String afficherEvenementsAdmin(Model model) {
        List<Evenement> evenements = evenementOpenFeign.getAllEvenements();
        model.addAttribute("evenements", evenements);
        return "evenements_admin";
    }

    @GetMapping("/reservations")
    public String showReservations(@RequestParam Long id_evenement, Model model) {
        List<Reservation> reservations =  reservationRepository.findByidReservation(id_evenement);
        List<Reservation> reservationListe = new ArrayList<>();;
        System.out.println(reservations);
        for (Reservation reservation : reservations) {
            System.out.println("*************************************************************************************************");
            Utilisateur utilisateur=utilisateurOpenFeign.getUtilisateurById(reservation.getIdUtilisateur());
            System.out.println("user: "+utilisateur);
            System.out.println("*************************************************************************************************");
            Evenement evenement=evenementOpenFeign.getEvenementById(reservation.getIdEvenement());
            System.out.println("evenement: "+evenement);
            reservation.setUtilisateur(utilisateur);
            reservation.setEvenement(evenement);
            reservationListe.add(reservation);
        }
        System.out.println("*************************************************************************************************");
        System.out.println(reservationListe);

        model.addAttribute("reservations", reservationListe);

        return "reservations";
    }


}
