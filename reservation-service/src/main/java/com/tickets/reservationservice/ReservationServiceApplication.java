package com.tickets.reservationservice;

import com.tickets.reservationservice.entities.Reservation;
import com.tickets.reservationservice.repositories.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class ReservationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ReservationRepository reservationRepository) {
        return args -> {
            Reservation reservation=Reservation.builder().siege_num(5).statut("Accepted").idUtilisateur(1).idEvenement(1).build();

            reservationRepository.save(reservation);

            List<Reservation> reservations=reservationRepository.findAll();
            System.out.println("testing ");
            for(Reservation r:reservations){
                System.out.println("nom: "+r.getSiege_num());
            }
        };
    }


}
