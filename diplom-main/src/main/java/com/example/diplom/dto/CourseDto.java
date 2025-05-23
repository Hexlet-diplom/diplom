package com.example.diplom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

    public List<String> getObjectives() {
        return objectives == null ? null : Collections.unmodifiableList(objectives);
    }

    public void setObjectives(List<String> objectives) {
        this.objectives = objectives == null ? null : new ArrayList<>(objectives);
    }
}
