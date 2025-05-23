package com.example.diplom.controller;

import com.example.diplom.exception.InvalidFilterException;
import com.example.diplom.model.Course;
import com.example.diplom.service.CourseService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Controller for handling course-related requests.
 */
@Controller
@RequestMapping("/courses")
public class CourseController {

    /**
     * Default number of items per page for pagination.
     */
    private static final int DEFAULT_PAGE_SIZE = 16;

    /**
     * Mapping of sorting keys to their display names for course sorting options.
     */
    private static final Map<String, String> SORT_OPTIONS = Map.of(
            "popular", "Popular",
            "newest", "Newest",
            "highest_rated", "Highest rating"
    );

    /**
     * Service for managing course-related operations.
     */
    @Autowired
    private final CourseService courseService;

    /**
     * Controller to handle course-related web requests.
     * <p>
     * Supports listing, filtering, sorting courses and displaying
     * individual course details.
     *
     * @param courseService the service for managing courses
     */
    @Autowired
    public CourseController(final CourseService courseService) {
        this.courseService = courseService;
    }

    /** Returns a paginated list of courses with optional search, filtering, and sorting.
     * @param page current page number, 1-based (default: 1)
     * @param search optional search query
     * @param filter optional category filter (default: ALL)
     * @param sort optional sort type (popular, newest, highest_rated; default: newest)
     * @param userDetails authenticated user (nullable)
     * @param model view model
     * @return Thymeleaf template name for course list
     */
    @GetMapping
    public String getCourses(
            @RequestParam(defaultValue = "1") @Min(1) final int page,
            @RequestParam(defaultValue = "") final String search,
            @RequestParam(defaultValue = "ALL") final String filter,
            @RequestParam(defaultValue = "newest") final String sort,
            @AuthenticationPrincipal final UserDetails userDetails,
            final Model model
    ) {
        final int validatedPage = Math.max(page, 1);

        final Pageable pageable = PageRequest.of(
                validatedPage - 1,
                DEFAULT_PAGE_SIZE,
                getSortStrategy(sort)
        );

        final Page<Course> coursesPage = courseService.getCourses(
                search,
                filter,
                pageable
        );

        model.addAttribute("courses", coursesPage.getContent());
        model.addAttribute("currentPage", validatedPage);
        model.addAttribute("totalPages", coursesPage.getTotalPages());
        model.addAttribute("sortOptions", SORT_OPTIONS);
        model.addAttribute("selectedSort", sort);
        model.addAttribute("searchQuery", search);
        model.addAttribute("selectedFilter", filter);

        return "courses/courses";
    }

    /**
     * Displays a single course with its lessons.
     *
     * @param model model to pass data to the view
     * @return name of the course page view
     */
    @GetMapping("/course-page/{id}")
    public String showCourse(@PathVariable final Long id, final Model model) {
        final Course course = courseService.getCourseWithLessons(id);
        model.addAttribute("course", course);
        return "course-page/course-page";
    }

    /**
     * Returns the sorting strategy based on the provided sort value.
     *
     * @param sort sort parameter
     * @return sorting strategy
     */
    private Sort getSortStrategy(final String sort) {
        return switch (sort) {
            case "popular" -> Sort.by("enrollmentCount").descending();
            case "highest_rated" -> Sort.by("rating").descending();
            default -> Sort.by("createdAt").descending();
        };
    }

    /**
     * Handles InvalidFilterException and returns a 400 error response.
     *
     * @param exception the InvalidFilterException thrown
     * @return response entity with error message
     */
    @ExceptionHandler(InvalidFilterException.class)
    public ResponseEntity<Map<String, String>> handleInvalidFilter(final InvalidFilterException exception) {
        return ResponseEntity
                .badRequest()
                .body(Map.of("error", exception.getMessage()));
    }
}
