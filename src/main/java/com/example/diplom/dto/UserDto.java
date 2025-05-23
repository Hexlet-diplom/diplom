package com.example.diplom.dto;

import com.example.diplom.model.Role;
import com.example.diplom.model.UserCourse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private Set<Role> roles;
    private Set<UserCourse> userCourses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserDto(Long id, String email, String password, Set<Role> roles,
                   Set<UserCourse> userCourses, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles == null ? null : Set.copyOf(roles);
        this.userCourses = userCourses == null ? null : Set.copyOf(userCourses);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Set<Role> getRoles() {
        return roles == null ? null : Set.copyOf(roles);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles == null ? null : Set.copyOf(roles);
    }

    public Set<UserCourse> getUserCourses() {
        return userCourses == null ? null : Set.copyOf(userCourses);
    }

    public void setUserCourses(Set<UserCourse> userCourses) {
        this.userCourses = userCourses == null ? null : Set.copyOf(userCourses);
    }
}
