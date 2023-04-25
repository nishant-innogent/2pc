package com.example.ProductService.controller;

import com.example.ProductService.entity.EventBus;
import com.example.ProductService.entity.Product;
import com.example.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
  @Autowired
  ProductService service;
  @Autowired
  EventBus eventBus;
  @PutMapping("/product/{id}")
  public Product update(@PathVariable("id")Integer id, @RequestParam("quantity")Long quantity){
    service.reduceQuantity(id, quantity);
    return eventBus.getEvent();
  }
}
