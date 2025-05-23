package com.example.diplom.controller;

import com.example.diplom.model.Course;
import com.example.diplom.service.CourseService;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Controller for handling general page requests such as home page,
 * course selector, pricing, FAQ and about pages.
 */
@Controller
public class MainController {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    /**
     * Default number of courses per page.
     */
    private static final int DEFAULT_PAGE_SIZE = 8;

    /**
     * Map of supported sorting options with their display names.
     */
    private static final Map<String, String> SORT_OPTIONS = Map.of(
            "popular", "Popular",
            "newest", "Newest",
            "highest_rated", "Highest rating"
    );

    /**
     * Service for course-related business logic.
     */
    @Autowired
    private final CourseService courseService;

    /**
     * Constructs a new PageController with injected CourseService.
     *
     * @param courseService the course service to use
     */
    @Autowired
    public MainController(final CourseService courseService) {
        this.courseService = courseService;
    }

    /** Home GET with paging, filtering, sorting, optional user.
     * @param page page (1-based)
     * @param search search query
     * @param filter category filter
     * @param sort sort key
     * @param userDetails user, nullable
     * @param model model
     * @return view name
     */
    @GetMapping("/")
    public String home(
            final @RequestParam(defaultValue = "1") @Min(1) int page,
            final @RequestParam(defaultValue = "") String search,
            final @RequestParam(defaultValue = "ALL") String filter,
            final @RequestParam(defaultValue = "popular") String sort,
            final @AuthenticationPrincipal UserDetails userDetails,
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

        return "home/index";
    }

    /**
     * Returns the course selector page.
     *
     * @return the view name for the course selector page
     */
    @GetMapping("/course-selector")
    public String courseSelector() {
        return "course-selector/course-selector";
    }

    /**
     * Returns the pricing page.
     *
     * @return the view name for the pricing page
     */
    @GetMapping("/pricing")
    public String pricing() {
        return "pricing/pricing";
    }

    /**
     * Returns the FAQ page.
     *
     * @return the view name for the FAQ page
     */
    @GetMapping("/faq")
    public String faq() {
        return "faq/faq";
    }

    /**
     * Returns the about page.
     *
     * @return the view name for the about page
     */
    @GetMapping("/about")
    public String about() {
        return "about/about";
    }

    /**
     * Returns a Sort object based on the provided sorting key.
     *
     * @param sort the sorting option key
     * @return the Sort object to apply to queries
     */
    private Sort getSortStrategy(final String sort) {
        return switch (sort) {
            case "popular" -> Sort.by("enrollmentCount").descending();
            case "highest_rated" -> Sort.by("rating").descending();
            default -> Sort.by("createdAt").descending();
        };
    }
}
