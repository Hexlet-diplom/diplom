package com.example.diplom.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String reason) {
        super("Category not found: " + reason);
    }
}