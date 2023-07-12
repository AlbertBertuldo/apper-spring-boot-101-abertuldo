package com.apper.theblogservice.service;

import com.apper.theblogservice.exceptions.BlogNotFoundException;
import com.apper.theblogservice.exceptions.BloggerNotFoundException;
import com.apper.theblogservice.exceptions.EmailAlreadyRegisteredException;
import com.apper.theblogservice.exceptions.InvalidBloggerIdException;
import com.apper.theblogservice.payload.ServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServiceError handleInvalidField(MethodArgumentNotValidException ex){
        for (ObjectError allError : ex.getBindingResult().getAllErrors()) {
            System.out.println(allError.getDefaultMessage());
        }

        return ex.getBindingResult().getAllErrors()
                .stream()
                .findFirst()
                .map(objectError -> new ServiceError("InvalidField", objectError.getDefaultMessage()))
                .orElse(new ServiceError("InvalidField", "Unknown invalid argument encountered"));

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    @ResponseBody
    public ServiceError handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException ex) {
        return new ServiceError("EmailAlreadyRegistered", ex.getMessage());
    }

    @ExceptionHandler(InvalidBloggerIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ResponseBody
    public ServiceError handleInvalidBloggerIdException(InvalidBloggerIdException ex) {
        return new ServiceError(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(BloggerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ServiceError handleBloggerNotFoundException(BloggerNotFoundException ex) {
        return new ServiceError(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(BlogNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ServiceError handleBlogNotFoundException(BlogNotFoundException ex) {
        return new ServiceError(ex.getClass().getSimpleName(), ex.getMessage());
    }

}