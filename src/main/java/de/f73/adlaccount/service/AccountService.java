package de.f73.adlaccount.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import de.f73.adlaccount.persistence.repositories.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    AccountRepository accountRepository;

    @Value("${SPRING_BASIC_AUTH_USER}")
    private String basicAuthUser;

    @Value("${SPRING_BASIC_AUTH_PW}")
    private String basicAuthPassword;

	public String getToken() {
        final String tokenData = basicAuthUser + ":" + basicAuthPassword;
        byte[] token = Base64.getEncoder().encode(tokenData.getBytes());
		return new String(token);
	}

}