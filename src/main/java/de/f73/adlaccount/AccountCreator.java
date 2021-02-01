package de.f73.adlaccount;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import de.f73.adlaccount.persistence.models.AccountEntity;
import de.f73.adlaccount.persistence.repositories.AccountRepository;

@Component
public class AccountCreator implements CommandLineRunner {
    Logger logger = LogManager.getLogger(AccountCreator.class);

    @Autowired
    private AccountRepository repository;

    @Override
    public void run(String... args) throws Exception {
        
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = bcryptPasswordEncoder.encode("rootPass123!");
        AccountEntity account = new AccountEntity();
        account.setFin("WVWZZZ1KZDP045466");
        account.setPassword(pwd);
        repository.save(account);

    }
}
