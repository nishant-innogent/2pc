package com.example.AccountService.repository;

import com.example.AccountService.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountEntityRepository extends JpaRepository<AccountEntity, Integer> {
}