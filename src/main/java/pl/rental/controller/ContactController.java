package pl.rental.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rental.model.Contact;
import pl.rental.model.User;
import pl.rental.service.ContactService;

import java.util.List;

@Controller
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact/form")
    public String showContactForm(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            return "redirect:/users/login";
        }
        return "contact-form";
    }

    @PostMapping("/contact/add")
    public String addContact(@RequestParam String message, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            return "redirect:/users/login";
        }
        contactService.saveContact(loggedInUser, message);
        return "redirect:/contact/messages";
    }

    @GetMapping("/contact/messages")
    public String showUserMessages(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            return "redirect:/users/login";
        }

        List<Contact> userContacts = contactService.getUserContacts(loggedInUser);
        model.addAttribute("userContacts", userContacts);

        return "user-contacts";
    }
}
