package de.f73.adlaccount.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import de.f73.adlaccount.persistence.repositories.AccountRepository;
import de.f73.adlaccount.service.AccountService;


@WebMvcTest(AccountController.class)
public class AccountControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AccountService mockAccountService;

    @MockBean
    private AccountRepository mockAccountRepository;

    @Test
    @DisplayName("should return 401 if not authorized")
    void returnNotAuthorizedIfNotAuthorized() throws Exception {

        mockMvc.perform(get("/fins").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

    }

    @Test
    @WithMockUser(username = "testUser", password = "testPW", roles = "USER")
    @DisplayName("should return a List of Fins")
    void returnCarDataEntities() throws Exception {

        when(mockAccountService.getFins()).thenReturn(Arrays.asList("fin1", "fin2", "fin3"));

        mockMvc.perform(get("/fins").characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        verify(mockAccountService).getFins();

    }

    
}
