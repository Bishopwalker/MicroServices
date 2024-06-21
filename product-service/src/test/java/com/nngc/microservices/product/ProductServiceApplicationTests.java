package com.nngc.microservices.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nngc.microservices.product.dto.ProductRequest;
import com.nngc.microservices.product.repository.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//
//@Testcontainers
//@SpringBootTest
//@AutoConfigureMockMvc
//class ProductServiceApplicationTests {
//
//    @Autowired
//    ProductRepo productRepo;
//
//
//    @Container
//    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
//
//    @Autowired
//    private ObjectMapper objectMapper;
//    //integration test
////Start MongoDB container
//    @Autowired
//    private MockMvc mockMvc;
//
//    //Set properties
//    @DynamicPropertySource
//    static void SetProperties(DynamicPropertyRegistry registry){
//        registry.add("spring.data.mongodb.uri=mongodb://localhost:27017/product-service", mongoDBContainer::getReplicaSetUrl);
//    }
//    @Test
//    void shouldCreateProduct() throws Exception {
//        int count = productRepo.findAll().size();
//      var info=  getProductRequest();
//      String productRequest = objectMapper.writeValueAsString(info);
//        mockMvc.perform(post("/api/product/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                        .content(productRequest))
//                .andExpect(status().isCreated());
//
//        Assertions.assertEquals(count+1, productRepo.count());
//
//    }
//
//
//
//    private ProductRequest getProductRequest(){
//        return ProductRequest.builder()
//                .name("Tester 1")
//                .description("Product 1 description")
//                .price(BigDecimal.valueOf(1300))
//                .build();
//    }
//}
