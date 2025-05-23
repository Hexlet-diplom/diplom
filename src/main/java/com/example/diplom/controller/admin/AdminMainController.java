package com.example.diplom.controller.admin;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
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
@NoArgsConstructor
public final class AdminMainController {

    /**
     * Handles GET requests to "/admin".
     *
     * @return the name of the admin panel view template
     */
    @GetMapping("/main")
    public String adminPanel() {
        return "admin/dashboard";
    }
}
