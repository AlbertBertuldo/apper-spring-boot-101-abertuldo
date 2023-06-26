package com.apper;

import com.apper.exceptions.AccountNotFoundException;
import com.apper.exceptions.UsernameAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private List<Account> accounts = new ArrayList<>();
    private IdGeneratorService idGeneratorService;

    @Autowired
    public AccountService(IdGeneratorService idGeneratorService) {
        this.idGeneratorService = idGeneratorService;
    }

    public Account create(String firstName, String lastName, String username, String clearPassword) throws UsernameAlreadyRegisteredException {

        if (isUsernameRegistered(username)) {
            throw new UsernameAlreadyRegisteredException("Username already registered.");
        }

        Account account = new Account();

        String id = idGeneratorService.getNextId();
        System.out.println("Generated id: " + id);

        account.setId(id);
        account.setBalance(1_000.0);

        LocalDateTime now = LocalDateTime.now();
        account.setCreationDate(now);
        account.setLastUpdated(now);

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setClearPassword(clearPassword);
        account.setVerificationCode(idGeneratorService.generateRandomCharacters(6));

        accounts.add(account);

        return account;
    }
    public boolean isUsernameRegistered(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public Account get(String accountId) throws AccountNotFoundException {
        return accounts.stream()
                .filter(account -> account.getId().equals(accountId))
                .findFirst()
                .orElseThrow(() -> new AccountNotFoundException("Account not found. AccountId: " + accountId));
    }

    public List<Account> getAll() throws AccountNotFoundException {
        if (accounts.isEmpty()) {
            throw new AccountNotFoundException("Account not found. Empty Accounts");
        }
        return accounts;
    }

    public void delete(String accountId) throws AccountNotFoundException {
        boolean accountExists = accounts.removeIf(account -> account.getId().equals(accountId));

        if (!accountExists) {
            throw new AccountNotFoundException("Account not found. AccountId: " + accountId);
        }
    }

    public void update(String accountId, String firstName, String lastName, String userName, String password) {
        for (Account account : accounts) {
            if (account.getId().equals(accountId)) {
                account.setFirstName(firstName);
                account.setLastName(lastName);
                account.setUsername(userName);
                account.setClearPassword(password);
                account.setLastUpdated(LocalDateTime.now());
                break;
            }
        }

    }
}