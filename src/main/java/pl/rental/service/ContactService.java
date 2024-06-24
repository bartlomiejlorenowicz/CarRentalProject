package pl.rental.service;

import org.springframework.stereotype.Service;
import pl.rental.model.Contact;
import pl.rental.model.User;
import pl.rental.repository.ContactRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void saveContact(User user, String message) {
        Contact contact = new Contact();
        contact.setUser(user);
        contact.setMessage(message);
        contact.setContactDate(LocalDateTime.now());
        contactRepository.save(contact);
    }

    public List<Contact> getUserContacts(User user) {
        return contactRepository.findByUserOrderByContactDateDesc(user);
    }
}
