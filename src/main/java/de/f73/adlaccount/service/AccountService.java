package de.f73.adlaccount.service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import de.f73.adlaccount.persistence.models.AccountEntity;
import de.f73.adlaccount.persistence.repositories.AccountRepository;

@Service
public class AccountService implements UserDetailsService {

    private static final Logger LOG = LogManager.getLogger("Service");

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

    public AccountEntity createAccount(AccountEntity accountEntity) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPwd = bcryptPasswordEncoder.encode(accountEntity.getPassword());
        accountEntity.setPassword(encryptedPwd);
        AccountEntity newAccount = accountRepository.save(accountEntity);
        LOG.info("account created for username: " + newAccount.getUsername());
        return newAccount;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity account = accountRepository.findByUsername(username);
        if (account == null) {
            LOG.error("username " + username + " not found");
            throw new UsernameNotFoundException(username);
        }
        return account;
    }

	public List<String> getFins() {
		return accountRepository.findAll().stream().map(account -> account.getUsername()).collect(Collectors.toList());
	}
}