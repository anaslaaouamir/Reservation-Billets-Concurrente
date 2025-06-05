package com.tickets.evenementservice.repositories;

import com.tickets.evenementservice.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {

}
