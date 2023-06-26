package com.apper;

import com.apper.exceptions.AccountNotFoundException;
import com.apper.exceptions.UsernameAlreadyRegisteredException;
import com.apper.request.CreateAccountRequest;
import com.apper.response.CreateAccountResponse;
import com.apper.response.GetAccountResponse;
import com.apper.response.PutAccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) throws UsernameAlreadyRegisteredException {
        Account account= accountService.create(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());

        CreateAccountResponse response = new CreateAccountResponse();
        response.setVerificationCode(account.getVerificationCode());

        return response;
    }


    //Spring Boot Rest API with Path variable, GetMapping used to Get resources using id parameter
    //{id} - URI template variable
    //localhost:8080/account/{id}
    @GetMapping("{accountId}")
    public GetAccountResponse getAccount(@PathVariable String accountId) throws AccountNotFoundException {
        Account account = accountService.get(accountId);

        return createGetAccountResponse(account);
    }

    //Spring Boot Rest API with Path variable, GetMapping used to Get ALL resources
    @GetMapping
    public List<GetAccountResponse> getAllAccounts() throws AccountNotFoundException{
        List<GetAccountResponse> responseList = new ArrayList<>();

        for (Account account : accountService.getAll()) {
            GetAccountResponse response = createGetAccountResponse(account);
            responseList.add(response);
        }

        return responseList;
    }

    private GetAccountResponse createGetAccountResponse(Account account) {
        GetAccountResponse response = new GetAccountResponse();
        response.setBalance(account.getBalance());
        response.setFirstName(account.getFirstName());
        response.setLastName(account.getLastName());
        response.setUsername(account.getUsername());
        response.setRegistrationDate(account.getCreationDate());
        response.setAccountId(account.getId());
        return response;
    }



    //Spring boot REST API that handles HTTP PUT Request - updating existing resource
    //localhost:8080/account/{id}
    @PutMapping("{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public PutAccountResponse updateAccount(@PathVariable String accountId, @RequestBody CreateAccountRequest request) throws AccountNotFoundException {
        Account account = accountService.get(accountId);

        accountService.update(accountId, request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());

        PutAccountResponse response = new PutAccountResponse();
        response.setLastUpdate(LocalDateTime.now());

        return response;
    }


    //Spring boot REST API that handles HTTP DELETE Request - deleting existing resource
    //localhost:8080/account/{id}
    @DeleteMapping("{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable String accountId) throws AccountNotFoundException {
        accountService.delete(accountId);
    }


}