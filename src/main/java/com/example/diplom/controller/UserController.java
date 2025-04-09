package com.example.diplom.controller;

import com.example.diplom.dto.RegistrationDto;
import com.example.diplom.mapper.UserMapper;
import com.example.diplom.model.User;
import com.example.diplom.repository.UserRepository;
import com.example.diplom.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, UserRepository userRepository) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationDto", new RegistrationDto());
        return "registration/register";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("registrationDto") @Valid RegistrationDto registrationDto,
            BindingResult bindingResult,
            Model model) {

        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.registrationDto", "Passwords do not match!");
            return "registration/register";
        }

        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            bindingResult.rejectValue("email", "error.registrationDto", "Email is already in use");
        }

        if (bindingResult.hasErrors()) {
            return "registration/register";
        }

        try {
            User user = userMapper.toEntity(registrationDto);
            userService.save(user);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            logger.error("Error during user registration: {}", e.getMessage(), e);
            return "registration/register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login/login";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        logger.error("Unhandled exception occurred: {}", ex.getMessage(), ex);
        model.addAttribute("error", "An unexpected error occurred. Please try again.");
        return "error";
    }
}
