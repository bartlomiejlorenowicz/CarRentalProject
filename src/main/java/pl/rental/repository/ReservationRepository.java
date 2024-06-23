package pl.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rental.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
