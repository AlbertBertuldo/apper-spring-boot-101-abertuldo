package com.apper.theblogservice;

import com.apper.theblogservice.exceptions.EmailAlreadyRegisteredException;
import com.apper.theblogservice.payload.ServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
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
        for(ObjectError allError : ex.getBindingResult().getAllErrors()){
            System.out.println(allError.getDefaultMessage());
        }

        return ex.getBindingResult().getAllErrors()
                .stream()
                .findFirst()
                .map(objectError -> new ServiceError(objectError.getDefaultMessage()))
                .orElse(new ServiceError("Unknown invalid argument encountered"));

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    @ResponseBody
    public ServiceError handleInvalidUserAgeException(EmailAlreadyRegisteredException ex) {
        return new ServiceError(ex.getMessage());
    }

}