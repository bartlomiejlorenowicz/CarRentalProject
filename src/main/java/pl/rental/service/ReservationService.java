package pl.rental.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rental.model.Reservation;
import pl.rental.model.User;
import pl.rental.repository.ReservationRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

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
        if (reservation.getReturnDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Return date cannot be in the past.");
        }

        reservation.setUser(user);
        reservation.setPaymentStatus("Pending");
        reservation.setReservationDate(LocalDateTime.now());

        Random random = new Random();
        BigDecimal randomDailyPrice = BigDecimal.valueOf(30 + (100 - 30) * random.nextDouble());
        reservation.setDailyPrice(randomDailyPrice);

        long rentalDays = ChronoUnit.DAYS.between(reservation.getReservationDate().toLocalDate(), reservation.getReturnDate().toLocalDate());
        reservation.setRentalDays(rentalDays);

        BigDecimal totalPrice = randomDailyPrice.multiply(BigDecimal.valueOf(rentalDays));
        reservation.setTotalPrice(totalPrice);

        if (reservation.getDailyPrice() == null || reservation.getTotalPrice() == null) {
            throw new IllegalArgumentException("Prices cannot be null");
        }

        reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservationByUser(Long userId) {
        return reservationRepository.findByUserUserId(userId);
    }
}
