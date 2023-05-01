package com.EmployeeAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmployeeControllerExceptionHandler {

        @ResponseStatus(HttpStatus.NOT_FOUND)
        public static class EmployeeNotFoundException extends RuntimeException {
            public EmployeeNotFoundException(Long id) {
                super("No Employee found at ID: " + id);
            }
        }

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public static class InvalidEmployeeDataException extends RuntimeException {
            public InvalidEmployeeDataException() {
                super("Invalid Employee data provided");
            }
        }

        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public static class EmployeeServiceException extends RuntimeException {
            public EmployeeServiceException(String message) {
                super(message);
            }
        }


}
