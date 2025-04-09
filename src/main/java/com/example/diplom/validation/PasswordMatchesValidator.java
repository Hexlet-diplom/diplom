package com.example.diplom.validation;

import com.example.diplom.dto.RegistrationDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegistrationDto> {

    @Override
    public boolean isValid(RegistrationDto registrationDto, ConstraintValidatorContext context) {
        return registrationDto.getPassword() != null && registrationDto.getPassword().equals(registrationDto.getConfirmPassword());
    }
}
