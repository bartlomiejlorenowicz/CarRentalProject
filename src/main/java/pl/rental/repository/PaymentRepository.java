package pl.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rental.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
