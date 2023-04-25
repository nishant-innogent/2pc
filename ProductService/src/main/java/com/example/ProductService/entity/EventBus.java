package com.example.ProductService.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class EventBus {
  List<Product> products = new ArrayList<>();
  public void addEvent(Product product){
    products = new ArrayList<>();
    products.add(product);
    log.info("add events : {}",products);
  }
  public Product getEvent(){
    log.info("get events : {}",products);
    while (products.isEmpty()){
      try {
        TimeUnit.MILLISECONDS.sleep(10);
      } catch (InterruptedException ex) {

      }
    }
    final Product product = products.get(0);
    products.remove(product);
    return product;
  }
}
