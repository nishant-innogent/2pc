package com.example.AccountService.controller;

import com.example.AccountService.entity.AccountEntity;
import com.example.AccountService.entity.EventBus;
import com.example.AccountService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
  @Autowired
  AccountService service;
  @Autowired
  EventBus eventBus;
  @PutMapping("/account/{id}")
  public AccountEntity deductAmount(@PathVariable("id")Integer id, @RequestParam("amount")Long amount){
    service.deductAmount(id, amount);
    return eventBus.getEvent();
  }
}
