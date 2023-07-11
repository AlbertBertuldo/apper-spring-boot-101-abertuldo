package com.apper.theblogservice.exceptions;


public class EmailAlreadyRegisteredException extends Exception {
    public EmailAlreadyRegisteredException(String message) {

        super(message);
    }
}

