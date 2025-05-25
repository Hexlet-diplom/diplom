package com.example.diplom.dto;

import com.example.diplom.model.UserCourse;
import com.example.diplom.model.enums.RoleName;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private Set<Long> roleIds;
    private Set<RoleName> roleNames;
    private Set<UserCourse> userCourses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
