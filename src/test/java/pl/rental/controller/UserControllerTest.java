package pl.rental.controller;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import pl.rental.dto.UserDto;
import pl.rental.model.Address;
import pl.rental.model.User;
import pl.rental.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    private UserService userService;
    private UserController userController;
    private Model model;
    private HttpSession session;

    @BeforeEach
    void setup() {
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);
        model = Mockito.mock(Model.class);
        session = Mockito.mock(HttpSession.class);
    }

    @Test
    void showRegistrationForm() {
        String viewName = userController.showRegistrationForm(model);
        verify(model).addAttribute(eq("user"), any(UserDto.class));
        assertEquals("register", viewName);
    }

    @Test
    void registerUser() {
        UserDto userDto = new UserDto();
        userDto.setUsername("john");
        userDto.setPassword("password");
        userDto.setEmail("john@example.com");
        userDto.setStreet("123 Main St");
        userDto.setCity("City");
        userDto.setZipCode("12345");
        userDto.setCountry("Country");

        String viewName = userController.registerUser(userDto);

        verify(userService).saveAddress(any(Address.class));
        verify(userService).saveUser(any(User.class));
        assertEquals("redirect:/users/login", viewName);
    }

    @Test
    void showDashBoard_LoggedIn() {
        when(session.getAttribute("user")).thenReturn(new User());
        String viewName = userController.showDashBoard(session, model);
        assertEquals("dashboard", viewName);
    }

    @Test
    void showDashBoard_NotLoggedIn() {
        when(session.getAttribute("user")).thenReturn(null);
        String viewName = userController.showDashBoard(session, model);
        assertEquals("redirect:/users/login", viewName);
    }

    @Test
    void showLoginForm() {
        String viewName = userController.showLoginForm();
        assertEquals("login", viewName);
    }

    @Test
    void loginUser_ValidUser() {
        User user = new User();
        when(userService.validateUser("john", "password")).thenReturn(user);

        String viewName = userController.loginUser("john", "password", session, model);

        verify(session).setAttribute("user", user);
        assertEquals("redirect:/users/dashboard", viewName);
    }

    @Test
    void loginUser_InvalidUser() {
        when(userService.validateUser("john", "password")).thenReturn(null);

        String viewName = userController.loginUser("john", "password", session, model);

        verify(model).addAttribute("error", "Invalid username or password");
        assertEquals("login", viewName);
    }

    @Test
    void logoutUser() {
        String viewName = userController.logoutUser(session);

        verify(session).invalidate();
        assertEquals("redirect:/users/login", viewName);
    }

    @Test
    void showProfile_LoggedIn() {
        User user = new User();
        when(session.getAttribute("user")).thenReturn(user);

        String viewName = userController.showProfile(session, model);

        verify(model).addAttribute("user", user);
        assertEquals("profile", viewName);
    }

    @Test
    void showProfile_NotLoggedIn() {
        when(session.getAttribute("user")).thenReturn(null);

        String viewName = userController.showProfile(session, model);

        assertEquals("redirect:/users/login", viewName);
    }

    @Test
    void updateProfile() {
        User user = new User();
        Address address = new Address();
        user.setAddress(address);
        when(session.getAttribute("user")).thenReturn(user);

        UserDto userDto = new UserDto();
        userDto.setEmail("newemail@example.com");
        userDto.setStreet("456 New St");
        userDto.setCity("New City");
        userDto.setZipCode("67890");
        userDto.setCountry("New Country");

        String viewName = userController.updateProfile(userDto, session, model);

        verify(userService).saveUser(user);
        verify(userService).saveAddress(address);
        verify(session).setAttribute("user", user);
        verify(model).addAttribute("success", "Profile updated successfully");

        assertEquals("profile", viewName);
    }

}
