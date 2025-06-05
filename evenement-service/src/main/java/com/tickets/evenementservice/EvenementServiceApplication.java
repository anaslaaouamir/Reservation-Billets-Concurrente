package com.tickets.evenementservice;

import com.tickets.evenementservice.entities.Evenement;
import com.tickets.evenementservice.repositories.EvenementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class EvenementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvenementServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(EvenementRepository evenementRepository) {
        return args -> {
            Evenement evenement=Evenement.builder().lieu("Rabat").titre("match maroc").nb_sieges(50000).build();

            evenementRepository.save(evenement);

            List<Evenement> evenements=evenementRepository.findAll();
            System.out.println("testing ");
            for(Evenement u:evenements){
                System.out.println("nom: "+u.getTitre());
            }
        };
    }

}
