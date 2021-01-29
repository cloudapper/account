package de.f73.adlaccount.persistence.models;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountEntity {
    @Id
    private Long id;
    private String fin;
    private String password;
}