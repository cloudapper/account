package de.f73.adlaccount.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.f73.adlaccount.persistence.models.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    public AccountEntity findByFin(String fin);
    
}