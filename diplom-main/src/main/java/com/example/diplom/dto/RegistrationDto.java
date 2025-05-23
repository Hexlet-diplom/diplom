package com.example.diplom.dto;

import com.example.diplom.validation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object for user registration.
 * Validates email, password, and password confirmation fields.
 */
@Data
@PasswordMatches(message = "Passwords must match")
public class RegistrationDto {

    /**
     * Minimum length for the password.
     */
    private static final int PASS_MIN_LEN = 6;

    /**
     * User's email address.
     */
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    /**
     * User's password.
     * Must be at least {@value #PASS_MIN_LEN} characters long,
     * contain at least one letter and one number.
     */
    @NotEmpty(message = "Password is required")
    @Size(min = PASS_MIN_LEN, message = "Password must be at least 6 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{" + PASS_MIN_LEN + ",}$",
            message = "Password must contain at least one letter and one number")
    private String password;

    /**
     * Confirmation of the user's password.
     */
    @NotEmpty(message = "Confirm Password is required")
    private String confirmPassword;
}
