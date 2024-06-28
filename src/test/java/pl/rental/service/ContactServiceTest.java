package pl.rental.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.rental.model.Contact;
import pl.rental.model.User;
import pl.rental.repository.ContactRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveContact() {
        User user = new User();
        user.setUserId(1L);
        String message = "Test message";

        contactService.saveContact(user, message);

        verify(contactRepository).save(any(Contact.class));
    }

    @Test
    public void testGetUserContacts() {
        User user = new User();
        user.setUserId(1L);

        Contact contact1 = new Contact();
        contact1.setUser(user);
        contact1.setMessage("Message 1");
        contact1.setContactDate(LocalDateTime.now().minusDays(1));

        Contact contact2 = new Contact();
        contact2.setUser(user);
        contact2.setMessage("Message 2");
        contact2.setContactDate(LocalDateTime.now());

        List<Contact> expectedContacts = Arrays.asList(contact2, contact1);

        when(contactRepository.findByUserOrderByContactDateDesc(user)).thenReturn(expectedContacts);

        List<Contact> actualContacts = contactService.getUserContacts(user);

        assertEquals(expectedContacts, actualContacts);
    }
}
