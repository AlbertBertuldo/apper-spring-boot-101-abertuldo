package com.apper.estore;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody //so that it is converted into json
    public ServiceError handleInvalidField(MethodArgumentNotValidException ex){

        //Coach Orvyl's implementation

        for(ObjectError allError : ex.getBindingResult().getAllErrors()){
            System.out.println(allError.getDefaultMessage());
        }

        //Gets the first error/exception, bindingresult - all error

        return ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(objectError -> new ServiceError(objectError.getDefaultMessage()))
                .orElse(new ServiceError("Unknown invalid argument encountered"));

        /****************************************************************************************************************************/
        //My implementation
        // Get all errors from the MethodArgumentNotValidException
//        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
//
//        // Check if any errors exist
//        if (!errors.isEmpty()) {
//            // Get the first error
//            ObjectError firstError = errors.get(0);
//            String errorMessage = firstError.getDefaultMessage();
//            return new ServiceError(errorMessage);
//        } else {
//            return new ServiceError("Unknown invalid argument encountered");
//        }
    }

}