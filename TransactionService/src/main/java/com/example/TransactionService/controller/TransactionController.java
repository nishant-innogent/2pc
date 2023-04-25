package com.example.TransactionService.controller;

import com.example.TransactionService.Events;
import com.example.TransactionService.enums.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
  @GetMapping("/transaction/account")
  public Boolean getStatusForAccount(){
    Events.accountStatus= Status.SUCCESS;
    while (Events.productStatus == Status.PROGRESS || Events.productStatus == Status.NOTSTARTED){

    }
    final boolean equals = Events.productStatus.equals(Status.SUCCESS);
    Events.productStatus = Status.NOTSTARTED;
    return equals;
  }
  @GetMapping("/transaction/product")
  public Boolean getStatusForProduct(){
    Events.productStatus= Status.SUCCESS;
    while (Events.accountStatus == Status.PROGRESS || Events.accountStatus == Status.NOTSTARTED){

    }
    final boolean equals = Events.accountStatus.equals(Status.SUCCESS);
    Events.accountStatus = Status.NOTSTARTED;
    return equals;
  }
  @GetMapping("/transaction/fail/account")
  public Boolean getStatusForFailAccount(){
    Events.accountStatus= Status.FAILED;
    Events.productStatus = Status.NOTSTARTED;
    return true;
  }
  @GetMapping("/transaction/fail/product")
  public Boolean getStatusForFailProduct(){
    Events.productStatus= Status.FAILED;
    Events.accountStatus = Status.NOTSTARTED;
    return true;
  }

}
