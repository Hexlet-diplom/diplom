package com.example.diplom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CourseDto {
    private Long id;
    private Long categoryId;
    private String level;
    private String name;
    private String subtitle;
    private String imageUrl;
    private String description;
    private List<String> objectives;
    private int totalDuration;
    private int enrollmentCount;
    private BigDecimal rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
}
