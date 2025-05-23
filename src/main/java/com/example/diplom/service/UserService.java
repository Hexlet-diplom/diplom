package com.example.diplom.service;

import com.example.diplom.model.User;
import com.example.diplom.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing user operations.
 */
@Service
public class UserService {

    /**
     * Logger instance for logging events in UserService.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /**
     * Repository for accessing User entities.
     */
    private final UserRepository userRepository;

    /**
     * Password encoder for hashing user passwords.
     */
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Constructs a UserService with the given repository and password encoder.
     *
     * @param userRepository  the user repository
     * @param passwordEncoder the password encoder
     */
    public UserService(final UserRepository userRepository, final BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Saves a new user if email is not already in use.
     *
     * @param user the user to save
     * @throws IllegalArgumentException if user with the same email already exists
     */
    @Transactional
    public void save(final User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("A user with this email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("User with email {} has been successfully registered", user.getEmail());
        }
    }

    /**
     * Retrieves all users.
     *
     * @return list of users
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Finds a user by ID.
     *
     * @param userId the ID of the user
     * @return the user if found, otherwise null
     */
    public User findById(final Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    /**
     * Deletes a user by ID.
     *
     * @param userId the ID of the user to delete
     */
    @Transactional
    public void delete(final Long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public User update(final Long userId, final User updatedUser) {
        Optional<User> existingOpt = userRepository.findById(userId);
        if (existingOpt.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }

        User existingUser = existingOpt.get();

        // Обновляем основные поля
        existingUser.setEmail(updatedUser.getEmail());

        // Если пароль обновлен и не пуст, хэшируем и сохраняем
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        // Обновляем роли — копируем через сеттер с защитой
        existingUser.setRoles(updatedUser.getRoles());

        // Можно добавить обновление других полей, если будут (например, имя и пр.)

        return userRepository.save(existingUser);
    }

    public User findByEmail(final String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public long countUsers() {
        return userRepository.count();
    }
}
