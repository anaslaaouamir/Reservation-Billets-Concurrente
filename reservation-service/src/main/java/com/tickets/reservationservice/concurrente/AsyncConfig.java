package com.tickets.reservationservice.concurrente;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {
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
