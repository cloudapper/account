package de.f73.adlaccount.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.f73.adlaccount.persistence.models.AccountEntity;
import de.f73.adlaccount.persistence.repositories.AccountRepository;
import de.f73.adlaccount.service.AccountService;

/**
 * CarDataController
 */
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository carDataEntityRepository;

    private static final Logger LOG = LogManager.getLogger("Controller") ;
    
    @GetMapping("/")
    public ResponseEntity<String> justSlash() {
        LOG.info("Request on /");
        return new ResponseEntity<>("You have reached the account Service - please use the defined endpoints! PS: Thanks Robert!", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> singUp(@RequestBody AccountEntity account) {
        LOG.info("Request on /signup, account fin: " + account.getUsername());
        AccountEntity newAccount = accountService.createAccount(account);
        LOG.info("Data saved: " + newAccount.getUsername());
        return new ResponseEntity<>(accountService.getToken(), HttpStatus.CREATED);
    }

    @GetMapping("/signin")
    public ResponseEntity<String> signIn() {
        LOG.info("Request on /signin");
        return new ResponseEntity<>(accountService.getToken(), HttpStatus.OK);
    }   

    @GetMapping("/fins")
    public ResponseEntity<List<String>> getFins() {
        LOG.info("Request on /fins");
        return new ResponseEntity<>(accountService.getFins(), HttpStatus.OK);
    }   
}