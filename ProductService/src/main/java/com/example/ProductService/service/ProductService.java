package com.example.ProductService.service;

import com.example.ProductService.entity.EventBus;
import com.example.ProductService.entity.Product;
import com.example.ProductService.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class ProductService {
  @Autowired
  ProductRepository repository;
  @Autowired
  ApplicationEventPublisher eventPublisher;
  @Autowired
  EventBus eventBus;

  @Transactional
  @Async
  public Product reduceQuantity(Integer id,Long quantity){
    log.info("id : {} quantity : {}",id,quantity);
    Product product = repository.findById(id).orElse(null);
    if(product.getQuantity()>quantity){
      product=Product.builder().id(id).quantity(product.getQuantity()-quantity).build();
    } else {
      new RestTemplate().exchange("http://localhost:8090/transaction/fail/product", HttpMethod.GET, null, Object.class);
      eventBus.addEvent(new Product());
      throw new RuntimeException();
    }
    eventPublisher.publishEvent(product);
    return repository.save(product);
  }

}
