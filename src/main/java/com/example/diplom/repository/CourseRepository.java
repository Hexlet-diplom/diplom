package com.example.diplom.repository;

import com.example.diplom.model.Category;
import com.example.diplom.model.Course;
import com.example.diplom.model.enums.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link Course} entities.
 * Extends JpaRepository for CRUD operations
 * and JpaSpecificationExecutor for filtering.
 */
public interface CourseRepository extends
        JpaRepository<Course, Long>,
        JpaSpecificationExecutor<Course> {

    /**
     * Finds all courses belonging to the specified category.
     *
     * @param category the category to filter by
     * @return list of courses in the given category
     */
    List<Course> findByCategory(Category category);

    /**
     * Finds all courses by their difficulty level.
     *
     * @param level the difficulty level (e.g., "beginner", "advanced")
     * @return list of courses matching the given level
     */
    List<Course> findByLevel(String level);

    /**
     * Finds a course by its ID and fetches associated lessons eagerly.
     *
     * @param courseId the course ID
     * @return optional course with lessons loaded
     */
    @EntityGraph(attributePaths = {"lessons", "objectives"})
    Optional<Course> findById(Long id);

    List<Course> findByStatus(CourseStatus status);

    Page<Course> findByStatusAndNameContainingIgnoreCase(CourseStatus status, String name, Pageable pageable);

    List<Course> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to);

    long countByStatus(CourseStatus status);

    @Transactional
    @Modifying
    @Query("UPDATE Course c SET c.status = :status WHERE c.id IN :ids")
    int updateStatusForIds(@Param("status") CourseStatus status, @Param("ids") List<Long> ids);


}
