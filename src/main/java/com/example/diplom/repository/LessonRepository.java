package com.example.diplom.repository;

import com.example.diplom.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repository for managing {@link Lesson} entities.
 * Provides CRUD operations and custom queries to fetch lessons
 * based on course ID and lesson order number.
 */
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    /**
     * Finds lessons in the specified course
     * with an order number greater than the current one.
     *
     * @param courseId the ID of the course
     * @param currentOrder the current lesson's order number
     * @return list of next lessons ordered ascending by order number
     */
    @Query("SELECT l FROM Lesson l "
            + "WHERE l.course.id = :courseId "
            + "AND l.orderNumber > :currentOrder "
            + "ORDER BY l.orderNumber ASC")
    List<Lesson> findNextLessons(
            @Param("courseId") Long courseId,
            @Param("currentOrder") Integer currentOrder
    );

    /**
     * Finds lessons in the specified course with
     * an order number less than the current one.
     *
     * @param courseId the ID of the course
     * @param currentOrder the current lesson's order number
     * @return list of previous lessons ordered descending by order number
     */
    @Query("SELECT l FROM Lesson l "
            + "WHERE l.course.id = :courseId "
            + "AND l.orderNumber < :currentOrder "
            + "ORDER BY l.orderNumber DESC")
    List<Lesson> findPreviousLessons(
            @Param("courseId") Long courseId,
            @Param("currentOrder") Integer currentOrder
    );
}
