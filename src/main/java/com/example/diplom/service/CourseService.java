package com.example.diplom.service;

import com.example.diplom.exception.CourseNotFoundException;
import com.example.diplom.exception.InvalidFilterException;
import com.example.diplom.model.Category;
import com.example.diplom.model.Course;
import com.example.diplom.model.enums.CourseStatus;
import com.example.diplom.repository.CourseRepository;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Service class responsible for managing {@link Course} entities.
 */
@Service
public class CourseService {

    /**
     * Repository for accessing course data.
     */
    private final CourseRepository courseRepository;

    /**
     * Service for accessing category data.
     */
    private final CategoryService categoryService;

    /**
     * Constructs a CourseService with the
     * specified course repository and category service.
     *
     * @param courseRepository the repository to manage courses
     * @param categoryService  the service to manage categories
     */
    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP2",
            justification = "Spring-managed service, safe to assign directly"
    )
    public CourseService(final CourseRepository courseRepository,
                         final CategoryService categoryService) {
        this.courseRepository = courseRepository;
        this.categoryService = categoryService;
    }

    /**
     * Creates a new course.
     *
     * @param name       course name
     * @param description course description
     * @param categoryName name of the category
     * @return created {@link Course}
     */
    @Transactional
    public Course createCourse(final String name, final String description, final String categoryName) {
        final Category category = categoryService.getCategoryByName(categoryName);

        final Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        course.setCategory(category);
        course.setStatus(CourseStatus.DRAFT); // по умолчанию, если нужно

        return courseRepository.save(course);
    }


    /**
     * Retrieves all courses by the given category name.
     *
     * @param categoryName the name of the category
     * @return list of courses in the specified category
     */
    public List<Course> getCoursesByCategory(final String categoryName) {
        final Category category = categoryService.getCategoryByName(categoryName);
        return courseRepository.findByCategory(category);
    }

    /**
     * Retrieves a course by its ID.
     *
     * @param courseId the ID of the course
     * @return the course with the given ID
     * @throws CourseNotFoundException if the course is not found
     */
    public Course getCourseById(final Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
    }

    /**
     * Retrieves a course along with its lessons.
     *
     * @param courseId the ID of the course
     * @return the course with its lessons
     * @throws CourseNotFoundException if the course is not found
     */
    public Course getCourseWithLessons(final Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
    }

    /** Returns paginated courses filtered by title and status.
     * @param search optional title query
     * @param filter course status filter ("ACTIVE", "DRAFT", etc.)
     * @param pageable pagination details
     * @return page of matching courses
     * @throws InvalidFilterException if filter is invalid
     */
    public Page<Course> getCourses(
            final String search,
            final String filter,
            final Pageable pageable
    ) {
        Specification<Course> spec = Specification.where(null);

        if (search != null && !search.isEmpty()) {
            final String searchLower = search.toLowerCase(Locale.ROOT);
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + searchLower + "%"));
        }

        if (filter != null && !filter.isEmpty() && !"ALL".equalsIgnoreCase(filter)) {
            try {
                final CourseStatus status =
                        CourseStatus.valueOf(filter.toUpperCase(Locale.ROOT));
                spec = spec.and((root, query, cb) ->
                        cb.equal(root.get("status"), status));
            } catch (IllegalArgumentException e) {
                throw new InvalidFilterException("Invalid filter value: " + filter);
            }
        }

        return courseRepository.findAll(spec, pageable);
    }

    /**
     * Retrieves all courses in the system.
     *
     * @return list of all courses
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    /**
     * Updates an existing course.
     *
     * @param courseId    ID of the course to update
     * @param name       new Name
     * @param description new description
     * @param categoryName new category name
     * @param status      new status
     * @return updated {@link Course}
     * @throws CourseNotFoundException if course not found
     */
    public Course updateCourse(final Long courseId,
                               final String name,
                               final String description,
                               final String categoryName,
                               final CourseStatus status) {
        final Course course = getCourseById(courseId);

        final Category category = categoryService.getCategoryByName(categoryName);

        course.setName(name);
        course.setDescription(description);
        course.setCategory(category);
        course.setStatus(status);

        return courseRepository.save(course);
    }

    /**
     * Deletes a course by ID.
     *
     * @param courseId ID of the course to delete
     * @throws CourseNotFoundException if course not found
     */
    public void deleteCourseById(final Long courseId) {
        final Course course = getCourseById(courseId);
        courseRepository.delete(course);
    }

    public long countCourses() {
        return courseRepository.count();
    }
}
