package com.apper.theblogservice.exceptions;

public class InvalidBloggerIdException extends Exception {
    public InvalidBloggerIdException(String message) {
        super(message);
    }
}