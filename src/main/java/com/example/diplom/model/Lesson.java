package com.example.diplom.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Lesson.
 * A lesson belongs to a course and has a unique order number within that course.
 */
@Data
@Entity
@Table(name = "lessons",
        uniqueConstraints = @UniqueConstraint(
                name = "uc_lesson_order",
                columnNames = {"course_id", "order_number"}))
public class Lesson {

    /**
     * Unique identifier of the lesson.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Course to which this lesson belongs.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @NotNull
    @Getter
    private Course course;

    /**
     * The sequential order number of the lesson within the course.
     */
    @Column(name = "order_number", nullable = false)
    private int orderNumber;

    /**
     * Title of the lesson.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Description text of the lesson.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Detailed content of the lesson.
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * List of media items associated with the lesson, stored as JSONB in the database.
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<MediaItem> media = new ArrayList<>();
}
