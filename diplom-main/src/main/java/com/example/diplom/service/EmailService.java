package com.example.diplom.service;

import com.example.diplom.dto.EmailFormDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service responsible for sending contact form data via email.
 */
@Service
public class EmailService {

    /**
     * JavaMailSender bean for sending emails.
     */
    private final JavaMailSender mailSender;

    /**
     * Support email address to which user messages are sent.
     */
    @Value("${support.email}")
    private String supportEmail;

    /**
     * Email address used as the sender when sending emails.
     */
    @Value("${spring.mail.username}")
    private String fromEmail;


    /**
     * Constructs EmailService with the provided mail sender.
     *
     * @param mailSender the JavaMailSender to send emails
     */
    public EmailService(final JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends an email with contact form details to the configured support email.
     *
     * @param contactForm the submitted contact form data
     */
    public void sendContactForm(final EmailFormDto contactForm) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(supportEmail);
        message.setSubject("New contact form submission: " + contactForm.getIssue());
        message.setText(createEmailText(contactForm));

        mailSender.send(message);
    }

    /**
     * Formats the contact form data into a readable email message.
     *
     * @param contactForm the submitted contact form data
     * @return the formatted message body
     */
    private String createEmailText(final EmailFormDto contactForm) {
        return String.format(
                "New contact form submission:%n%n"
                        + "Name: %s%n"
                        + "Email: %s%n"
                        + "Phone: %s%n"
                        + "Issue: %s%n%n"
                        + "Message:%n%s",
                contactForm.getName(),
                contactForm.getEmail(),
                contactForm.getPhone(),
                contactForm.getIssue(),
                contactForm.getMessage()
        );
    }

}
