package com.EmployeeAPI.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("No Employee found at ID: " + id);
    }
}

