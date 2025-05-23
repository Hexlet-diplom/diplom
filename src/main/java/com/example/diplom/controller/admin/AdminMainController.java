package com.example.diplom.controller.admin;

import com.example.diplom.service.CourseService;
import com.example.diplom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for admin-related endpoints.
 * <p>
 * This class handles requests to the admin panel.
 * If you plan to extend this class,
 * override methods carefully to avoid breaking behavior.
 */
@Controller
@RequestMapping("/admin/dashboard")
@RequiredArgsConstructor
public final class AdminMainController {

    private final UserService userService;
    private final CourseService courseService;
    /**
     * Handles GET requests to "/admin".
     *
     * @return the name of the admin panel view template
     */
    @GetMapping("/main")
    public String adminPanel(Model model) {
        long totalUsers = userService.countUsers();
        long totalCourses = courseService.countCourses();

        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalCourses", totalCourses);

        return "admin/dashboard";
    }
}
