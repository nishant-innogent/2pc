package com.example.ProductService.service;


import com.example.ProductService.entity.EventBus;
import com.example.ProductService.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.client.RestTemplate;

@Component
public class TransactionEventService {
  @Autowired
  EventBus eventBus;
  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void handleEvent(Product product){
    eventBus.addEvent(product);
    Boolean status = new RestTemplate().exchange("http://localhost:8090/transaction/product", HttpMethod.GET, null, Boolean.class).getBody();
    if(!status)
      throw new RuntimeException();
  }
}
