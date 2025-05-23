package com.example.diplom.repository;

import com.example.diplom.model.User;
import com.example.diplom.model.enums.RoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findAllByRoles_Name(RoleName roleName);

    List<User> findByEmailContainingIgnoreCase(String emailPart);

    List<User> findByRoles_NameAndEmailContainingIgnoreCase(RoleName roleName, String emailPart);

    Page<User> findAll(Pageable pageable);

    // Метод с пагинацией и фильтрацией по роли и email — полезно для админки
    Page<User> findByRoles_NameAndEmailContainingIgnoreCase(RoleName roleName, String emailPart, Pageable pageable);

    @Transactional
    long deleteByEmail(String email);

    // Проекция для админки — минимум данных для вывода
    interface UserSummary {
        Long getId();
        String getEmail();
        // Можно добавить, например, Set<RoleName> или Set<String> getRoles(); если в User есть связь с ролями
    }

    List<UserSummary> findAllProjectedBy();
}
