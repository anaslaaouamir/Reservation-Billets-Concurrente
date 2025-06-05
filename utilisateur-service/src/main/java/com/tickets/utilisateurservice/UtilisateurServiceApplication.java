package com.tickets.utilisateurservice;

import com.tickets.utilisateurservice.entities.Utilisateur;
import com.tickets.utilisateurservice.repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients

public class UtilisateurServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtilisateurServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init( UtilisateurRepository utilisateurRepository) {
        return args -> {
           Utilisateur user=Utilisateur.builder().email("anas@gmail.com").nom("anas").build();

            utilisateurRepository.save(user);

            List<Utilisateur> utilisateurs=utilisateurRepository.findAll();
            System.out.println("testing ");
            for(Utilisateur u:utilisateurs){
                System.out.println("nom: "+u.getNom());
            }
        };
    }

}
