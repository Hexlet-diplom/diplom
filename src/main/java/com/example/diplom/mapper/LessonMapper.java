package com.example.diplom.mapper;

import com.example.diplom.dto.LessonDto;
import com.example.diplom.model.Course;
import com.example.diplom.model.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {

    public static LessonDto toDto(Lesson lesson) {
        if (lesson == null) {
            return null;
        }

        LessonDto dto = new LessonDto();
        dto.setId(lesson.getId());
        dto.setCourseId(lesson.getCourse().getId());
        dto.setOrderNumber(lesson.getOrderNumber());
        dto.setName(lesson.getName());
        dto.setDescription(lesson.getDescription());
        dto.setContent(lesson.getContent());
        dto.setMedia(lesson.getMedia());
        return dto;
    }

    public static Lesson toEntity(LessonDto dto, Course course) {
        if (dto == null || course == null) {
            return null;
        }

        Lesson lesson = new Lesson();
        lesson.setId(dto.getId());
        lesson.setCourse(course);
        lesson.setOrderNumber(dto.getOrderNumber());
        lesson.setName(dto.getName());
        lesson.setDescription(dto.getDescription());
        lesson.setContent(dto.getContent());
        lesson.setMedia(dto.getMedia());
        return lesson;
    }
}
