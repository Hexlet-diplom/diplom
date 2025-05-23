package com.example.diplom.mapper;

import com.example.diplom.dto.RegistrationDto;
import com.example.diplom.model.Role;
import com.example.diplom.model.User;
import com.example.diplom.model.enums.RoleName;
import com.example.diplom.repository.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Locale;


@Component
public class RegistrationMapper {

    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegistrationMapper(final RoleRepository roleRepository,
                              final BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User toEntity(final RegistrationDto dto) {
        final String emailLower = dto.getEmail().toLowerCase(Locale.ROOT);

        User user = new User();
        user.setEmail(emailLower);
        user.setPassword(dto.getPassword());

        Role userRole = getUserRole();
        user.setRoles(Collections.singleton(userRole));

        return user;
    }

    private Role getUserRole() {
        return roleRepository.findByName(RoleName.USER)
                .orElseThrow(() -> new IllegalStateException("Role USER not found"));
    }
}
