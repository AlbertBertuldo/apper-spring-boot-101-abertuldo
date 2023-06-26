package com.apper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {




    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    //Spring Boot Rest API that handles HTTP POST Request - creating new resource
    //@PostMapping - for HTTP Post request
    //@RequestBody - responsible for retrieving the HTTP Request body and automatically converting it to java object
    //CreateAccountResponse - must return verification code

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request){
        Account account = accountService.create(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());

        CreateAccountResponse response = new CreateAccountResponse();
        response.setVerificationCode(account.getVerificationCode());

        return response;
    }

}
