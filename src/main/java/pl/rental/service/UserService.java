package pl.rental.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.rental.model.User;
import pl.rental.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
