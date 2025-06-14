package com.tickets.reservationservice.concurrente;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {

    // Configuration d’un exécuteur de tâches asynchrones avec un pool de threads
// Permet d’exécuter certaines opérations (ex. : génération de ticket) en arrière-plan
// sans bloquer le traitement principal des requêtes

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(10);  // Minimum threads alive
        executor.setMaxPoolSize(20);   // Max threads allowed
        executor.setQueueCapacity(100); // Tasks waiting queue size
        executor.setThreadNamePrefix("ReservationTicket-");
        executor.initialize();

        return executor;
    }
}
