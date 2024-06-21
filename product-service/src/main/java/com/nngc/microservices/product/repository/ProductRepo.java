package com.nngc.microservices.product.repository;

import com.nngc.microservices.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepo extends MongoRepository<Product, String> {



}


