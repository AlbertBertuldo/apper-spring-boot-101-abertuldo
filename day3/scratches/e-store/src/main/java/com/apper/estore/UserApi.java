package com.apper.estore;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)  // must be 201
    //@Valid used or helps to validate response
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest request) {
        System.out.println(request);
        return new CreateUserResponse("RANDOM_CODE_FOR_NOW");
    }
}
