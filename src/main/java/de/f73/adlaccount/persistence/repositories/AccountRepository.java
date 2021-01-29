package de.f73.adlaccount.persistence.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import de.f73.adlaccount.persistence.models.AccountEntity;

public interface AccountRepository extends PagingAndSortingRepository<AccountEntity, Long> {
    
}