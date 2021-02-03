package de.f73.adlaccount.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import de.f73.adlaccount.persistence.models.AccountEntity;
import de.f73.adlaccount.persistence.repositories.AccountRepository;

@SpringBootTest
public class AccountServiceTests {
    
    @Autowired
    AccountService accountService;

    @MockBean
    AccountRepository accountRepository;

    @Test
    @DisplayName("create returns Entity with id")
    public void createEntity() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setPassword("password");
        AccountEntity accountEntityWithId = new AccountEntity();
        accountEntityWithId.setId(1L);

        when(accountRepository.save(any(AccountEntity.class))).thenReturn(accountEntityWithId);

        final AccountEntity actual = accountService.createAccount(accountEntity);

        assertThat(actual.getId()).isEqualTo(accountEntityWithId.getId());
    }
}
