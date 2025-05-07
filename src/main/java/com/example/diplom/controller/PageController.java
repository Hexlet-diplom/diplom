package com.example.diplom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @GetMapping("/")
    public String home() {
        logger.info("Accessed home page");
        return "home/index";
    }

    @GetMapping("/course-selector")
    public String courseSelector() {
        return "course-selector/course-selector";
    }

    @GetMapping("/pricing")
    public String pricing() {
        return "pricing/pricing";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq/faq";
    }

}
