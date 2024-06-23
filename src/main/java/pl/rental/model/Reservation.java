package pl.rental.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(nullable = false)
    private LocalDateTime reservationDate;

    @Column(nullable = false)
    private LocalDateTime returnDate;

    @Column(nullable = false)

    private String paymentStatus;
}
