package com.example.diplom.mapper;

import com.example.diplom.dto.UserCourseDto;
import com.example.diplom.model.Course;
import com.example.diplom.model.User;
import com.example.diplom.model.UserCourse;
import org.springframework.stereotype.Component;

@Component
public class UserCourseMapper {

    public UserCourseDto toDto(UserCourse entity) {
        if (entity == null) {
            return null;
        }

        return UserCourseDto.builder()
                .id(entity.getId())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .courseId(entity.getCourse() != null ? entity.getCourse().getId() : null)
                .enrolledAt(entity.getEnrolledAt())
                .status(entity.getStatus())
                .build();
    }

    public UserCourse toEntity(UserCourseDto dto, User user, Course course) {
        if (dto == null) {
            return null;
        }

        return UserCourse.builder()
                .id(dto.getId())
                .user(user)
                .course(course)
                .enrolledAt(dto.getEnrolledAt())
                .status(dto.getStatus())
                .build();
    }
}
