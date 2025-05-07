package com.example.diplom.controller;

import com.example.diplom.dto.ContactFormDto;
import com.example.diplom.service.EmailService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping("/contacts")
public class ContactController {

    private final EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public String showContactForm(Model model) {
        if (!model.containsAttribute("contactFormDto")) {
            model.addAttribute("contactFormDto", new ContactFormDto());
        }
        return "contacts/contacts";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> handleContactForm(
            @Valid @ModelAttribute ContactFormDto contactFormDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "success", false,
                            "message", "Validation failed. Please check your input."
                    ));
        }

        try {
            emailService.sendContactForm(contactFormDto);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Thank you for your message! We'll get back to you soon."
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body(Map.of(
                            "success", false,
                            "message", "An error occurred while sending your message. Please try again later."
                    ));
        }
    }
}
