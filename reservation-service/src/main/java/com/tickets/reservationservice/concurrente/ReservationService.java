package com.tickets.reservationservice.concurrente;

import com.tickets.reservationservice.entities.Reservation;
import com.tickets.reservationservice.entities.Ticket;
import com.tickets.reservationservice.repositories.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.concurrent.locks.ReentrantLock;

@Service
public class ReservationService {

    private final LockManager lockManager;
    private final ReservationRepository reservationRepository;
    private final TicketService ticketService;

    public ReservationService(LockManager lockManager, ReservationRepository reservationRepository,TicketService ticketService) {
        this.lockManager = lockManager;
        this.reservationRepository = reservationRepository;
        this.ticketService = ticketService;
    }

    //effectuer une reservatiion et appeler la fonction qui genere le ticket (utilisation du ReentrantLock)
    @Transactional
    public void makeReservation(Long userId, Long eventId, int seatNumber) {


        String lockKey = eventId + "_" + seatNumber;
        ReentrantLock lock = lockManager.getLock(lockKey);

        lock.lock();  // Acquire lock for this seat
        try {
            boolean alreadyReserved = reservationRepository.existsByIdEvenementAndSiegeNum(eventId, seatNumber);
            if (alreadyReserved) {
                throw new RuntimeException("Seat already reserved!");
            }

            // si en veut faire un temps entre la verrification du siège et l'ajout de la reservation

            /*try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }*/

            Reservation reservation = new Reservation(userId, eventId, seatNumber);
            reservationRepository.save(reservation);

            ticketService.generateTicket(reservation.getIdReservation());

            //si on veut tester la generation du ticket avec une fonction sans @async
            //ticketService.generateTicket1(reservation.getIdReservation());

        }catch (Exception e) {
            throw new RuntimeException("Failed to generate ticket: " + e.getMessage());
        }
        finally {
            lock.unlock();  // Release the lock
        }



    }

    //effectuer une reservatiion et appeler la fonction qui genere le ticket (sans utilisation du ReentrantLock)
    @Transactional
    public void makeReservationWithoutLock(Long userId, Long eventId, int seatNumber) {
        boolean alreadyReserved = reservationRepository.existsByIdEvenementAndSiegeNum(eventId, seatNumber);
        if (alreadyReserved) {
            throw new RuntimeException("Seat already reserved!");
        }

        try {
            Thread.sleep(10000); // 2 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Reservation reservation = new Reservation(userId, eventId, seatNumber);
        reservationRepository.save(reservation);
        ticketService.generateTicket(reservation.getIdReservation());
    }


}
