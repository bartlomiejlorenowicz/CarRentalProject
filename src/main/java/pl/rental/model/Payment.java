package pl.rental.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDate paymentDate;
}
