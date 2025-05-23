package com.example.diplom.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * DTO for the contact form submitted by users.
 * Contains fields for name, email, phone, issue, and message.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailFormDto {

    /**
     * Maximum length of a message.
     */
    private static final int MESSAGE_MAX_LEN = 1000;

    /**
     * Minimum length of a phone number.
     */
    private static final int PHONE_MIN_LEN = 10;

    /**
     * Maximum length of a phone number.
     */
    private static final int PHONE_MAX_LEN = 20;
    /**
     * User's full name.
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * User's email address.
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    /**
     * User's phone number.
     * Supports various international phone number formats.
     */
    @NotBlank(message = "Phone is required")
    @Size(min = PHONE_MIN_LEN, max = PHONE_MAX_LEN, message = "Phone number must be between 10 and 20 characters long")
    @Pattern(
            regexp = "^(\\+\\d{1,3}|8)?[\\s\\-]?(\\(?\\d{3,5}\\)?)[\\s\\-]?\\d{1,4}[\\s\\-]?\\d{1,4}[\\s\\-]?\\d{0,4}$",
            message = "Invalid phone number"
    )
    private String phone;

    /**
     * The issue or topic selected by the user.
     */
    @NotBlank(message = "Please select an issue")
    private String issue;

    /**
     * Message content submitted by the user.
     * Maximum length is {@value #MESSAGE_MAX_LEN} characters.
     */
    @NotBlank(message = "Message cannot be empty")
    @Size(max = MESSAGE_MAX_LEN, message = "Message too long (max 1000 chars)")
    private String message;
}
