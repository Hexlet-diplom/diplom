package com.example.diplom.mapper;

import com.example.diplom.dto.UserDto;
import com.example.diplom.model.Role;
import com.example.diplom.model.User;
import com.example.diplom.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    // Пример для метода toDto(User user)
    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());

        // Заполняем roleIds, если нужно
        if (user.getRoles() != null) {
            dto.setRoleIds(user.getRoles().stream()
                    .map(role -> role.getId())
                    .collect(Collectors.toSet()));

            // Заполняем roleNames
            dto.setRoleNames(user.getRoles().stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toSet()));
        }

        return dto;
    }

    public static User toEntity(UserDto dto, RoleRepository roleRepository) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        if (dto.getRoleIds() != null) {
            Set<Role> roles = dto.getRoleIds().stream()
                    .map(roleId -> roleRepository.findById(roleId)
                            .orElseThrow(() -> new IllegalArgumentException("Role not found with id " + roleId)))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        } else {
            user.setRoles(Set.of());
        }

        user.setUserCourses(dto.getUserCourses());
        user.setCreatedAt(dto.getCreatedAt());
        user.setUpdatedAt(dto.getUpdatedAt());

        return user;
    }
}
