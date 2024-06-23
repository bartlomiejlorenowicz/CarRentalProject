package pl.rental.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rental.model.Reservation;
import pl.rental.model.User;
import pl.rental.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @Transactional
    public void saveReservation(Reservation reservation, User user) {
        reservation.setUser(user);
        reservation.setPaymentStatus("Pending");
        reservation.setReservationDate(LocalDateTime.now());
        reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservationByUser(User user) {
        return reservationRepository.findByUserUserId(user);
    }
}
