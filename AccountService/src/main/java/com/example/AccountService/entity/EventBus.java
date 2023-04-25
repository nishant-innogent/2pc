package com.example.AccountService.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class EventBus {
  List<AccountEntity> products = new ArrayList<>();
  public void addEvent(AccountEntity product){
    log.info("add events : {}",products);
    products=new ArrayList<>();
    products.add(product);
  }
  public AccountEntity getEvent(){
    log.info("get events : {}",products);
    while (products.isEmpty()){
      try {
        TimeUnit.MILLISECONDS.sleep(10);
      } catch (InterruptedException ex) {

      }
    }
    final AccountEntity product = products.get(0);
    products.remove(product);
    return product;
  }
}
