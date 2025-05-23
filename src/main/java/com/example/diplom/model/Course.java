package com.example.diplom.model;

import com.example.diplom.model.audit.Auditable;
import com.example.diplom.model.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Entity class representing a Course.
 * Contains information about the course, including its category, lessons,
 * and metadata.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
@SuppressWarnings("PMD.ExcessiveImports")
public class Course extends Auditable {

    /**
     * Minimum allowed length for the course title.
     */
    private static final int TITLE_MIN_LENGTH = 5;

    /**
     * Maximum allowed length for the course title.
     */
    private static final int TITLE_MAX_LENGTH = 255;

    /**
     * Precision (total number of digits) for the course rating.
     */
    private static final int RATING_PRECISION = 4;

    /**
     * Scale (number of digits after decimal point) for the course rating.
     */
    private static final int RATING_SCALE = 2;

    /**
     * Unique identifier of the course.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Category this course belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull
    @JsonBackReference
    private Category category;

    /**
     * Level of the course (e.g., Beginner, Intermediate).
     */
    @Column(nullable = false)
    @Size(min = 3, max = 100)
    @NotNull
    private String level;

    /**
     * Title of the course.
     * Must be between {@value #TITLE_MIN_LENGTH} and {@value #TITLE_MAX_LENGTH} characters.
     */
    @Column(nullable = false, length = TITLE_MAX_LENGTH)
    @Size(min = TITLE_MIN_LENGTH, max = TITLE_MAX_LENGTH)
    @NotNull
    private String name;

    /**
     * Optional subtitle of the course.
     */
    @Column
    private String subtitle;

    /**
     * URL to the course image.
     */
    @Column(nullable = false)
    @NotNull
    private String imageUrl;

    /**
     * Detailed course description.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * List of objectives/goals of the course.
     */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "course_objectives",
            joinColumns = @JoinColumn(name = "course_id")
    )
    @OrderColumn(name = "order_index")
    @Column(name = "objective")
    private List<String> objectives = new ArrayList<>();

    /**
     * List of lessons associated with the course.
     */
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lesson> lessons = new ArrayList<>();

    /**
     * Total duration of the course in minutes.
     */
    @Min(0)
    @Column(name = "total_duration")
    private int totalDuration;

    /**
     * Number of users enrolled in the course.
     */
    @Min(0)
    @Column(name = "enrollment_count")
    private int enrollmentCount;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCourse> userCourses = new HashSet<>();

    /**
     * Average rating of the course.
     */
    @Column(precision = RATING_PRECISION, scale = RATING_SCALE)
    private BigDecimal rating;

    /**
     * Date and time when the course was created.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Date and time when the course was last updated.
     */
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Status of the course.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseStatus status = CourseStatus.DRAFT;

    public Long getCategoryId() {
        return category != null ? category.getId() : null;
    }
}
