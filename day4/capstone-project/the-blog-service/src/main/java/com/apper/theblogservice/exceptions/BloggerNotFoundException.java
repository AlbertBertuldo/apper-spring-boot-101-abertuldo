package com.apper.theblogservice.exceptions;

public class BloggerNotFoundException extends Exception {
    public BloggerNotFoundException(String message) {
        super(message);
    }
}
