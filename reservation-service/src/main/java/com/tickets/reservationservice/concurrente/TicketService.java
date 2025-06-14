package com.tickets.reservationservice.concurrente;

import com.tickets.reservationservice.entities.Reservation;
import com.tickets.reservationservice.entities.Ticket;
import com.tickets.reservationservice.models.Evenement;
import com.tickets.reservationservice.models.Utilisateur;
import com.tickets.reservationservice.repositories.ReservationRepository;
import com.tickets.reservationservice.repositories.TicketRepository;
import com.tickets.reservationservice.web.EvenementOpenFeign;
import com.tickets.reservationservice.web.UtilisateurOpenFeign;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.concurrent.CompletableFuture;

@Service
public class TicketService {

    private final EvenementOpenFeign evenementOpenFeign;
    private final UtilisateurOpenFeign utilisateurOpenFeign;
    private final ReservationRepository reservationRepository;
    private final TicketRepository ticketRepository;


    public TicketService(EvenementOpenFeign evenementOpenFeign, UtilisateurOpenFeign utilisateurOpenFeign, ReservationRepository reservationRepository,TicketRepository ticketRepository) {
        this.evenementOpenFeign=evenementOpenFeign;
        this.utilisateurOpenFeign=utilisateurOpenFeign;
        this.reservationRepository=reservationRepository;
        this.ticketRepository=ticketRepository;


    }

    //generation du ticket utilisant @Async
    @Async("taskExecutor")
    public CompletableFuture<Ticket> generateTicket(Long reservationId) {

        Ticket ticket =null;
        Reservation reservation=reservationRepository.findById(reservationId).get();

        // Simulate ticket generation delay
        try {
            System.out.println(Thread.currentThread().getName() + " - Generating ticket for reservation " + reservationId);

            Utilisateur utilisateur=utilisateurOpenFeign.getUtilisateurById(reservation.getIdUtilisateur());
            Evenement evenement=evenementOpenFeign.getEvenementById(reservation.getIdEvenement());

            ticket=Ticket.builder().
                    idUtilisateur((long) utilisateur.getIdUtilisateur()).
                    idReservation(reservationId).
                    NomUtilisateur(utilisateur.getNom()).
                    titreEvenement(evenement.getTitre()).
                    dateEvenement(evenement.getDateEvenement()).
                    siegeNum(reservation.getSiegeNum()).
                    lieu(evenement.getLieu())
            .build();

            //une simulation : supposons que la generation du ticket prend, pour visualiser l'impacte
            Thread.sleep(20000); // 3 seconds delay for example
            System.out.println(Thread.currentThread().getName() + " - Ticket generated for reservation " + reservationId);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Return completed future
        ticketRepository.save(ticket);


        return CompletableFuture.completedFuture(ticket);

    }

    //generatioon du ticket sans utilisatoin de @Async
    public void generateTicket1(Long reservationId) {

        Ticket ticket =null;
        Reservation reservation=reservationRepository.findById(reservationId).get();

        // Simulate ticket generation delay
        try {
            System.out.println(Thread.currentThread().getName() + " - Generating ticket for reservation " + reservationId);

            Utilisateur utilisateur=utilisateurOpenFeign.getUtilisateurById(reservation.getIdUtilisateur());
            Evenement evenement=evenementOpenFeign.getEvenementById(reservation.getIdEvenement());

            ticket=Ticket.builder().
                    idUtilisateur((long) utilisateur.getIdUtilisateur()).
                    idReservation(reservationId).
                    NomUtilisateur(utilisateur.getNom()).
                    titreEvenement(evenement.getTitre()).
                    dateEvenement(evenement.getDateEvenement()).
                    siegeNum(reservation.getSiegeNum()).
                    lieu(evenement.getLieu())
                    .build();

            //une simulation : supposons que la generation du ticket prend, pour visualiser l'impacte
            Thread.sleep(20000); // 3 seconds delay for example
            System.out.println(Thread.currentThread().getName() + " - Ticket generated for reservation " + reservationId);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Return completed future

    }



    //original
    /*@Async("taskExecutor")
    public CompletableFuture<Void> generateTicket(Long reservationId) {

        // Simulate ticket generation delay
        try {
            System.out.println(Thread.currentThread().getName() + " - Generating ticket for reservation " + reservationId);

            Thread.sleep(20000); // 3 seconds delay for example
            System.out.println(Thread.currentThread().getName() + " - Ticket generated for reservation " + reservationId);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Return completed future
        return CompletableFuture.completedFuture(null);
    }*/


}
