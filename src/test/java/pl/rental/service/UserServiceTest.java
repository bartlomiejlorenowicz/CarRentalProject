package pl.rental.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.rental.model.Address;
import pl.rental.model.User;
import pl.rental.repository.AddressRepository;
import pl.rental.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        userService.saveUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testSaveAddress() {
        Address address = new Address();
        address.setCity("TestCity");
        address.setStreet("TestStreet");

        userService.saveAddress(address);

        verify(addressRepository, times(1)).save(address);
    }

    @Test
    public void testValidateUser_Success() {
        String username = "testuser";
        String password = "password";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        when(userRepository.findByUsername(username)).thenReturn(user);

        User validatedUser = userService.validateUser(username, password);

        assertEquals(user, validatedUser);
    }

    @Test
    public void testValidateUser_Failure() {
        String username = "testuser";
        String password = "password";

        when(userRepository.findByUsername(username)).thenReturn(null);

        User validatedUser = userService.validateUser(username, password);

        assertNull(validatedUser);
    }

    @Test
    public void testValidateUser_WrongPassword() {
        String username = "testuser";
        String password = "password";
        User user = new User();
        user.setUsername(username);
        user.setPassword("wrongpassword");

        when(userRepository.findByUsername(username)).thenReturn(user);

        User validatedUser = userService.validateUser(username, password);

        assertNull(validatedUser);
    }
}
