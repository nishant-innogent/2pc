package com.example.OrderService.controller;

import com.example.OrderService.response.AccountResponse;
import com.example.OrderService.response.OrderResponse;
import com.example.OrderService.response.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
  @GetMapping("/order")
  public OrderResponse createOrder(){
    RestTemplate restTemplate = new RestTemplate();
    log.info("calling account service");
    final AccountResponse account = restTemplate.exchange("http://localhost:8081/account/1?amount=200", HttpMethod.PUT, null, AccountResponse.class).getBody();
    log.info("account service resp : {}",account);
    log.info("calling product service");
    final ProductResponse product = restTemplate.exchange("http://localhost:8082/product/1?quantity=20", HttpMethod.PUT, null, ProductResponse.class).getBody();
    log.info("product service resp : {}",product);
    String status;
    String message;
    if(account.getAmount()>0 && product.getQuantity()>0){
      status = "success";
      message = "Order placed Successfully";
    }else {
      status = "failed";
      message = "could not place order....";
      message+= account.getAmount()>0?"quantity = "+product.getQuantity():"amount ="+account.getAmount();
    }
    return OrderResponse.builder().status(status).message(message).build();
  }
}
