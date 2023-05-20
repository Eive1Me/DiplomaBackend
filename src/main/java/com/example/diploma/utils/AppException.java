package com.example.diploma.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@Getter
public class AppException extends RuntimeException{

    private final String message;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class DatabaseException extends AppException {
        public DatabaseException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFoundException extends AppException {
        public NotFoundException(String message) {
            super(message);
        }
    }

}
