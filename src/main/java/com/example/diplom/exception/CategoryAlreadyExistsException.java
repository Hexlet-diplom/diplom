package com.example.diplom.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String name) {
        super("Category with name '" + name + "' already exists.");
    }
}
