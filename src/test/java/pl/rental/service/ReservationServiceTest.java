package pl.rental.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.rental.model.Reservation;
import pl.rental.model.User;
import pl.rental.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveReservation() {
        User user = new User();
        user.setUserId(1L);

        Reservation reservation = new Reservation();
        reservation.setReturnDate(LocalDateTime.now().plusDays(5));

        reservationService.saveReservation(reservation, user);

        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    public void testSaveReservationWithPastReturnDate() {
        User user = new User();
        user.setUserId(1L);

        Reservation reservation = new Reservation();
        reservation.setReturnDate(LocalDateTime.now().minusDays(1));

        assertThrows(IllegalArgumentException.class, () -> reservationService.saveReservation(reservation, user));
    }

    @Test
    public void testGetAllReservationByUser() {
        User user = new User();
        user.setUserId(1L);

        Reservation reservation1 = new Reservation();
        reservation1.setUser(user);

        Reservation reservation2 = new Reservation();
        reservation2.setUser(user);

        List<Reservation> expectedReservations = Arrays.asList(reservation1, reservation2);

        when(reservationRepository.findByUserUserId(user.getUserId())).thenReturn(expectedReservations);

        List<Reservation> actualReservations = reservationService.getAllReservationByUser(user.getUserId());

        assertEquals(expectedReservations, actualReservations);
    }
}
