package com.example.diplom.controller;

import com.example.diplom.dto.RegistrationDto;
import com.example.diplom.mapper.RegistrationMapper;
import com.example.diplom.model.User;
import com.example.diplom.repository.UserRepository;
import com.example.diplom.service.UserService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Locale;

/**
 * Controller to handle user registration, login, and error processing.
 */
@Controller
public class UserController {

    /**
     * Logger instance for UserController.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * The view name for the registration page.
     */
    private static final String REGISTRATION_VIEW = "registration/register";

    /**
     * Service for managing users.
     */
    private final UserService userService;

    /**
     * Mapper to convert between User DTOs and entities.
     */
    private final RegistrationMapper registrationMapper;

    /**
     * Repository interface for accessing user data.
     */
    private final UserRepository userRepository;

    /**
     * Service for loading user-specific data during authentication.
     */
    private final UserDetailsService userDetailsSvc;

    /**
     * Manager responsible for authentication operations.
     */
    private final AuthenticationManager authManager;


    /**
     * Constructs a UserController.
     *
     * @param userService user management service
     * @param registrationMapper maps DTOs and entities
     * @param userRepository user repository
     * @param userDetailsSvc loads user data for auth
     * @param authManager handles authentication
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Spring-managed dependencies are safe to assign")
    @Autowired
    public UserController(final UserService userService,
                          final RegistrationMapper registrationMapper,
                          final UserRepository userRepository,
                          final UserDetailsService userDetailsSvc,
                          final AuthenticationManager authManager) {
        this.userService = userService;
        this.registrationMapper = registrationMapper;
        this.userRepository = userRepository;
        this.userDetailsSvc = userDetailsSvc;
        this.authManager = authManager;
    }


    /**
     * Shows the registration form.
     *
     * @param model MVC model to pass attributes
     * @return registration page view name
     */
    @GetMapping("/register")
    public String showRegistrationForm(final Model model) {
        model.addAttribute("registrationDto", new RegistrationDto());
        return REGISTRATION_VIEW;
    }

    /**
     * Handles the submission of the registration form.
     * Validates input, registers the user, and performs auto-login.
     *
     * @param registrationDto DTO with user input
     * @param bindingResult   binding result for validation errors
     * @param model           MVC model to pass attributes
     * @param request         HTTP request for session setup
     * @return redirect to homepage or registration page on error
     */
    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("registrationDto") @Valid final RegistrationDto registrationDto,
            final BindingResult bindingResult,
            final Model model,
            final HttpServletRequest request) {

        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.registrationDto", "Passwords do not match!");
        }

        if (userRepository.existsByEmail(registrationDto.getEmail().toLowerCase(Locale.ROOT))) {
            bindingResult.rejectValue("email", "error.registrationDto", "Email is already in use");
        }

        if (bindingResult.hasErrors()) {
            return REGISTRATION_VIEW;
        }

        try {
            final User user = registrationMapper.toEntity(registrationDto);
            userService.save(user);

            authenticateUser(user.getEmail(), registrationDto.getPassword(), request);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            LOGGER.error("Error during user registration: {}", e.getMessage(), e);
            return REGISTRATION_VIEW;
        }
    }

    /**
     * Authenticates user after successful registration.
     *
     * @param email      user email
     * @param rawPassword raw password to authenticate
     * @param request     HTTP request object
     */
    private void authenticateUser(final String email, final String rawPassword, final HttpServletRequest request) {
        try {
            final UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(email, rawPassword);

            final Authentication authentication = authManager.authenticate(authToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            final HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        } catch (BadCredentialsException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Authentication failed for user: {}", email);
            }
            throw new IllegalArgumentException("Invalid credentials", e);
        }
    }

    /**
     * Shows the login form.
     *
     * @return login page view name
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login/login";
    }

    /**
     * Global exception handler for unhandled exceptions.
     *
     * @param exception    exception object
     * @param model MVC model to pass error message
     * @return error page view name
     */
    @ExceptionHandler(Exception.class)
    public String handleException(final Exception exception, final Model model) {
        LOGGER.error("Unhandled exception occurred: {}", exception.getMessage(), exception);
        model.addAttribute("error", "An unexpected error occurred. Please try again.");
        return "error";
    }
}
