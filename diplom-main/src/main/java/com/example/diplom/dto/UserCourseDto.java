package com.example.diplom.dto;

import com.example.diplom.model.enums.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseDto {
    private Long id;
    private Long userId;
    private Long courseId;
    private LocalDateTime enrolledAt;
    private EnrollmentStatus status;
}
