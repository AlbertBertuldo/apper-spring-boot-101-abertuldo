package com.apper.estore.payload;

public class InvalidUserAgeException extends RuntimeException {
    public InvalidUserAgeException(String message) {

        super(message);
    }
}
