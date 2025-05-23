package com.example.diplom.mapper;

import com.example.diplom.dto.CourseDto;
import com.example.diplom.model.Category;
import com.example.diplom.model.Course;
import com.example.diplom.model.enums.CourseStatus;

import java.util.Optional;

public class CourseMapper {

    public CourseDto toDto(Course course) {
        if (course == null) {
            return null;
        }

        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setCategoryId(Optional.ofNullable(course.getCategory()).map(Category::getId).orElse(null));
        dto.setLevel(course.getLevel());
        dto.setName(course.getName());
        dto.setSubtitle(course.getSubtitle());
        dto.setImageUrl(course.getImageUrl());
        dto.setDescription(course.getDescription());
        dto.setObjectives(course.getObjectives());
        dto.setTotalDuration(course.getTotalDuration());
        dto.setEnrollmentCount(course.getEnrollmentCount());
        dto.setRating(course.getRating());
        dto.setCreatedAt(course.getCreatedAt());
        dto.setUpdatedAt(course.getUpdatedAt());
        dto.setStatus(course.getStatus().name());
        return dto;
    }

    public Course toEntity(CourseDto dto, Category category) {
        if (dto == null || category == null) {
            return null;
        }

        Course course = new Course();
        course.setId(dto.getId());
        course.setCategory(category);
        course.setLevel(dto.getLevel());
        course.setName(dto.getName());
        course.setSubtitle(dto.getSubtitle());
        course.setImageUrl(dto.getImageUrl());
        course.setDescription(dto.getDescription());
        course.setObjectives(dto.getObjectives());
        course.setTotalDuration(dto.getTotalDuration());
        course.setEnrollmentCount(dto.getEnrollmentCount());
        course.setRating(dto.getRating());
        course.setCreatedAt(dto.getCreatedAt());
        course.setUpdatedAt(dto.getUpdatedAt());

        if (dto.getStatus() != null) {
            course.setStatus(CourseStatus.valueOf(dto.getStatus()));
        }

        return course;
    }
}
