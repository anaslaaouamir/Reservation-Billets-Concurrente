package com.tickets.reservationservice.concurrente;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Component

public class LockManager {

    // Gestionnaire de verrous pour assurer la synchronisation par clé
// Utilisé pour éviter les conflits lors des réservations concurrentes sur une même ressource (ex. : un siège)


    private final ConcurrentHashMap<String, ReentrantLock> locks = new ConcurrentHashMap<>();

    public ReentrantLock getLock(String key) {
        return locks.computeIfAbsent(key, k -> new ReentrantLock());
    }
}
