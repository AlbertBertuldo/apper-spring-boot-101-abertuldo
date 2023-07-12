package com.apper.theblogservice.exceptions;

public class BlogNotFoundException extends Exception {
    public BlogNotFoundException(String message) {
        super(message);
    }
}
