package com.example.diplom.service;

import com.example.diplom.dto.ContactFormDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${support.email}")
    private String supportEmail;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendContactForm(ContactFormDto contactForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(supportEmail);
        message.setSubject("New contact form submission: " + contactForm.getIssue());
        message.setText(createEmailText(contactForm));

        mailSender.send(message);
    }

    private String createEmailText(ContactFormDto contactForm) {
        return String.format(
                "New contact form submission:\n\n" +
                        "Name: %s\n" +
                        "Email: %s\n" +
                        "Phone: %s\n" +
                        "Issue: %s\n\n" +
                        "Message:\n%s",
                contactForm.getName(),
                contactForm.getEmail(),
                contactForm.getPhone(),
                contactForm.getIssue(),
                contactForm.getMessage()
        );
    }
}