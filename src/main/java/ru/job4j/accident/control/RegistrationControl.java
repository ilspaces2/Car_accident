package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.exception.UsernameAlreadyExistsException;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationControl {

    private final UserService users;

    public RegistrationControl(UserService users) {
        this.users = users;
    }

    @PostMapping("")
    public String save(@ModelAttribute User user) {
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("")
    public String registrationPage() {
        return "registration";
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public String handleException(UsernameAlreadyExistsException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "registration";
    }
}
