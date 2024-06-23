package pl.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rental.model.Reservation;
import pl.rental.model.User;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    public List<Reservation> findAll();
    List<Reservation> findByUserUserId(User user);
}
