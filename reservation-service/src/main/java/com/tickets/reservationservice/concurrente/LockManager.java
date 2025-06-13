package com.tickets.reservationservice.concurrente;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Component

public class LockManager {

    private final ConcurrentHashMap<String, ReentrantLock> locks = new ConcurrentHashMap<>();

    public ReentrantLock getLock(String key) {
        return locks.computeIfAbsent(key, k -> new ReentrantLock());
    }
}
