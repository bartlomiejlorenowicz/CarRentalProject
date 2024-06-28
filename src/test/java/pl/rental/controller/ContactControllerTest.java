package pl.rental.controller;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import pl.rental.model.Contact;
import pl.rental.model.User;
import pl.rental.service.ContactService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ContactControllerTest {

    private ContactService contactService;
    private ContactController contactController;
    private HttpSession session;
    private Model model;

    @BeforeEach
    void setup() {
        contactService = Mockito.mock(ContactService.class);
        session = Mockito.mock(HttpSession.class);
        model = Mockito.mock(Model.class);
        contactController = new ContactController(contactService);
    }

    @Test
    void showContactForm_UserLoggedIn() {
        User loggedInUser = new User();
        when(session.getAttribute("user")).thenReturn(loggedInUser);

        String viewName = contactController.showContactForm(session);

        assertEquals("contact-form", viewName);
    }

    @Test
    void showContactForm_UserNotLoggedIn() {
        when(session.getAttribute("user")).thenReturn(null);

        String viewName = contactController.showContactForm(session);

        assertEquals("redirect:/users/login", viewName);
    }

    @Test
    void addContact_UserLoggedIn() {
        User loggedInUser = new User();
        when(session.getAttribute("user")).thenReturn(loggedInUser);

        String viewName = contactController.addContact("Test message", session);

        verify(contactService).saveContact(loggedInUser, "Test message");
        assertEquals("redirect:/contact/messages", viewName);
    }

    @Test
    void addContact_UserNotLoggedIn() {
        when(session.getAttribute("user")).thenReturn(null);

        String viewName = contactController.addContact("Test message", session);

        assertEquals("redirect:/users/login", viewName);
    }

    @Test
    void showUserMessages_UserLoggedIn() {
        User loggedInUser = new User();
        List<Contact> userContacts = Arrays.asList(new Contact(), new Contact());
        when(session.getAttribute("user")).thenReturn(loggedInUser);
        when(contactService.getUserContacts(loggedInUser)).thenReturn(userContacts);

        String viewName = contactController.showUserMessages(session, model);

        verify(model).addAttribute("userContacts", userContacts);
        assertEquals("user-contacts", viewName);
    }

    @Test
    void showUserMessages_UserNotLoggedIn() {
        when(session.getAttribute("user")).thenReturn(null);

        String viewName = contactController.showUserMessages(session, model);

        assertEquals("redirect:/users/login", viewName);
    }
}
