package com.example.diplom.config;

import com.example.diplom.model.Role;
import com.example.diplom.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        roleRepository.findByName("ROLE_USER").orElseGet(() -> {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            return roleRepository.save(userRole);
        });

        roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            return roleRepository.save(adminRole);
        });
    }
}
