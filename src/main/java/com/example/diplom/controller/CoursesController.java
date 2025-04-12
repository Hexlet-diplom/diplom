package com.example.diplom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoursesController {
    @GetMapping("/courses")
    public String courses(Model model) {
        return "courses/courses";
    }

    @GetMapping("/course-page")
    public String coursePage(Model model) {
        return "course-page/course-page";
    }
}
