package pl.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rental.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
