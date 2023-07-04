package com.apper.estore;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@RestController
@RequestMapping("user")
public class UserApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)  // must be 201
    //@Valid used or helps to validate response
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) throws InvalidUserAgeException {
        System.out.println(request);

        // Compute age. Review LocalDate
        LocalDate birthDate = LocalDate.parse(request.getBirthDate());
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();



        // if age is below 15, throw an InvalidUserAgeException(you must create this exception)
        // create new method in your controller advice class that handles InvalidUserAgeException.class
        if (age < 15) {
            throw new InvalidUserAgeException("User must be at least 15 years old.");
        }

        return new CreateUserResponse("RANDOM_CODE_FOR_NOW");
    }
}
