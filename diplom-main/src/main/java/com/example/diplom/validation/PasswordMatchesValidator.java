package com.example.diplom.validation;

import com.example.diplom.dto.RegistrationDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;

/**
 * Validator for {@link PasswordMatches} annotation.
 * Ensures that password and confirmPassword fields are equal.
 */
@NoArgsConstructor
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegistrationDto> {

    @Override
    public boolean isValid(final RegistrationDto registrationDto, final ConstraintValidatorContext context) {
        return registrationDto.getPassword() != null
                && registrationDto.getPassword().equals(registrationDto.getConfirmPassword());
    }
}
