package com.example.AccountService.service;

import com.example.AccountService.entity.AccountEntity;
import com.example.AccountService.entity.EventBus;
import com.example.AccountService.repository.AccountEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class AccountService {
  @Autowired
  AccountEntityRepository repository;
  @Autowired
  ApplicationEventPublisher eventPublisher;
  @Autowired
  EventBus eventBus;
  @Transactional
  @Async
  public AccountEntity deductAmount(Integer id, Long amount){
    AccountEntity entity=repository.findById(id).get();
    if(entity.getAmount()>amount){
      entity = AccountEntity.builder().id(id).amount(entity.getAmount()-amount).build();
    } else {
      new RestTemplate().exchange("http://localhost:8090/transaction/fail/account", HttpMethod.GET, null, Object.class);
      eventBus.addEvent(new AccountEntity());
      throw new RuntimeException();
    }
    eventPublisher.publishEvent(entity);
    return repository.save(entity);
  }
}
