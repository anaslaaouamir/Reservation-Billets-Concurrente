package com.tickets.reservationservice.web;

import com.tickets.reservationservice.models.Evenement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "EVENEMENT-SERVICE")
public interface EvenementOpenFeign {

    @GetMapping("/evenements")
    public List<Evenement> getAllEvenements();

    @GetMapping("/evenement/{idEvenement}")
    public Evenement getEvenementById(@PathVariable long idEvenement);
}
