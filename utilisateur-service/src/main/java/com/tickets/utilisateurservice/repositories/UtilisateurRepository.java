package com.tickets.utilisateurservice.repositories;

import com.tickets.utilisateurservice.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}
