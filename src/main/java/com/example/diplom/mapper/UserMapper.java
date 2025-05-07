package com.example.diplom.mapper;

import com.example.diplom.dto.RegistrationDto;
import com.example.diplom.model.Role;
import com.example.diplom.model.User;
import com.example.diplom.repository.RoleRepository;
import com.example.diplom.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class UserMapper {

    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserMapper(RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User toEntity(RegistrationDto dto) {

        if (userRepository.existsByEmail(dto.getEmail().toLowerCase())) {
            throw new RuntimeException("User with email " + dto.getEmail() + " already exists.");
        }

        User user = new User();
        user.setEmail(dto.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        // Получение роли по имени
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role 'ROLE_USER' not found"));
        user.setRoles(Set.of(userRole));

        return user;
    }

}