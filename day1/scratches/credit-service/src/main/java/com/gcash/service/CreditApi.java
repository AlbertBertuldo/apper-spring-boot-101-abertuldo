package com.gcash.service;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CreditApi {
    private final CreditService creditService;

    public CreditApi(CreditService creditService) {
        this.creditService = creditService;
    }

    //Create an account
    @PostMapping
    public Account createNewAccount(@RequestBody Double initialBalance){
        return creditService.createAccount(initialBalance);
    }


    //retrieve all accounts
    @GetMapping
    public List<Account> getAllAccounts(){
        return creditService.getAllAccounts();
    }
}
