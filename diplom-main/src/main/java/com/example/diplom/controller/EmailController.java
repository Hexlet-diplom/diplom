package com.example.diplom.controller;

import com.example.diplom.dto.EmailFormDto;
import com.example.diplom.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Controller for handling contact form requests.
 */
@Controller
@RequestMapping("/contacts")
public class EmailController {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    /**
     * A key indicating the successful completion of the operation in the response.
     */
    private static final String KEY_SUCCESS = "success";

    /**
     * Key indicating the message to be displayed in the response.
     */
    private static final String KEY_MESSAGE = "message";

    /**
     * Service for sending email messages.
     */
    private final EmailService emailService;

    /**
     * Constructs the controller with the provided email service.
     *
     * @param emailService the service to send emails
     */
    public EmailController(final EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Displays the contact form page.
     *
     * @param model the UI model
     * @return the name of the contact form view
     */
    @GetMapping
    public String showEmailForm(final Model model) {
        if (!model.containsAttribute("EmailFormDto")) {
            model.addAttribute("EmailFormDto", new EmailFormDto());
        }
        return "contacts/contacts";
    }

    /**
     * Handles submission of the contact form.
     *
     * @param emailFormDto the submitted contact form data
     * @param bindingResult the result of validation
     * @return response entity indicating success or failure
     */
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> handleEmailForm(
            @Valid @ModelAttribute final EmailFormDto emailFormDto,
            final BindingResult bindingResult) {

        ResponseEntity<?> response;

        if (bindingResult.hasErrors()) {
            response = ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            KEY_SUCCESS, false,
                            KEY_MESSAGE, "Validation failed. Please check your input."
                    ));
        } else {
            try {
                emailService.sendContactForm(emailFormDto);
                response = ResponseEntity.ok(Map.of(
                        KEY_SUCCESS, true,
                        KEY_MESSAGE, "Thank you for your message! We'll get back to you soon."
                ));
            } catch (MailException e) {
                LOGGER.error("Failed to send contact form email", e);
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of(
                                KEY_SUCCESS, false,
                                KEY_MESSAGE, "Failed to send email. Please try again later."
                        ));
            } catch (RuntimeException e) {
                LOGGER.error("Unexpected error while processing contact form", e);
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of(
                                KEY_SUCCESS, false,
                                KEY_MESSAGE, "Something went wrong. Please try again later."
                        ));
            }
        }
        return response;
    }
}
