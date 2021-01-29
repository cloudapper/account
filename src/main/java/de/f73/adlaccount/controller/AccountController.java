package de.f73.adlaccount.controller;

import java.time.LocalDateTime;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String singUp(@RequestBody AccountEntity account) {
        LOG.info("Data saved: " + account.getFin());
        return "TODO";
    }

    @GetMapping("/signin")
    public String signIn() {
        LOG.info("Request on /signin");
        return accountService.getToken();
    }   
}