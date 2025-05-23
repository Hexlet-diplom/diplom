package com.example.diplom.repository;

import com.example.diplom.model.Role;
import com.example.diplom.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for {@link Role} entity.
 * Provides methods to perform CRUD operations and find roles by name.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a role by its name.
     *
     * @param name the name of the role
     * @return an Optional containing the found Role, or empty if not found
     */
    Optional<Role> findByName(RoleName name);
}
