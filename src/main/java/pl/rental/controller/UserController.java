package pl.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.rental.dto.UserDto;
import pl.rental.model.Address;
import pl.rental.model.User;
import pl.rental.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDTO") UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword((userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRole("USER");

        Address address = new Address();
        address.setStreet(userDto.getStreet());
        address.setCity(userDto.getCity());
        address.setZipCode(userDto.getZipCode());
        address.setCountry(userDto.getCountry());

        user.setAddress(address);

        userService.saveUser(user);
        return "redirect:/login";
    }
}
