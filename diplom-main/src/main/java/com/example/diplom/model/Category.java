package com.example.diplom.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Category.
 * Categories contain multiple courses.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categories")
public class Category {

    /**
     * Maximum allowed length for the name field.
     */
    private static final int NAME_MAX_LENGTH = 100;

    /**
     * Unique identifier of the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the category. Must be unique, not blank,
     * and limited to {@value #NAME_MAX_LENGTH} characters.
     */
    @Column(nullable = false, unique = true, length = NAME_MAX_LENGTH)
    @NotBlank(message = "Category name must not be blank")
    @Size(max = NAME_MAX_LENGTH, message = "Name must be at most " + NAME_MAX_LENGTH + " characters long")
    @ToString.Include
    private String name;

    /**
     * Description of the category.
     */
    @Column(columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "Description must not be blank")
    private String description;

    /**
     * List of courses belonging to this category.
     */
    @OneToMany(
            mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Course> courses = new ArrayList<>();

    public List<Course> getCourses() {
        return courses == null ? null : List.copyOf(courses);
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses == null ? new ArrayList<>() : new ArrayList<>(courses);
    }
}
