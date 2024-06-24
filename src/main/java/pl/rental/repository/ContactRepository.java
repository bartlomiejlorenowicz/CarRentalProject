package pl.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rental.model.Contact;
import pl.rental.model.User;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByUserOrderByContactDateDesc(User user);

    List<Contact> getContactsByUser(User user);
}
