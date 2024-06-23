package pl.rental.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.rental.model.Address;
import pl.rental.model.User;
import pl.rental.repository.AddressRepository;
import pl.rental.repository.UserRepository;

@Service
public class UserService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public UserService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

    public User validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
