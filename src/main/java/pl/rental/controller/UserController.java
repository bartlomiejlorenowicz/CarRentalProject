package pl.rental.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.rental.dto.UserDto;
import pl.rental.model.Address;
import pl.rental.model.User;
import pl.rental.service.UserService;

@Controller
@RequestMapping("/users")
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

        userService.saveAddress(address);
        userService.saveUser(user);
        return "redirect:/users/login";
    }

    @GetMapping("/dashboard")
    public String showDashBoard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/users/login";
        }
        return "dashboard";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session, Model model) {
        User user = userService.validateUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/users/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/users/login";
        }
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("user") UserDto userDto, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/users/login";
        }

        user.setEmail(userDto.getEmail());
        Address address = user.getAddress();
        address.setStreet(userDto.getStreet());
        address.setCity(userDto.getCity());
        address.setZipCode(userDto.getZipCode());
        address.setCountry(userDto.getCountry());

        userService.saveUser(user);
        userService.saveAddress(address);

        session.setAttribute("user", user);
        model.addAttribute("success", "Profile updated successfully");
        return "profile";
    }
}
