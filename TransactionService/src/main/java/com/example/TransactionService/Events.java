package com.example.TransactionService;

import com.example.TransactionService.enums.Status;
import org.springframework.stereotype.Component;

@Component
public class Events {
  public static Status accountStatus = Status.NOTSTARTED;
  public static Status productStatus = Status.NOTSTARTED;
}
