package com.nngc.microservices.product.service;

import com.nngc.microservices.product.dto.ProductResponse;
import com.nngc.microservices.product.entity.Product;
import com.nngc.microservices.product.repository.ProductRepo;
import com.nngc.microservices.product.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {

    private final ProductRepo productRepo;



        public void createProduct(ProductRequest productRequest) {
            Product product = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .build();

            productRepo.save(product);
            log.info("Product created: {}", product.getId());
        }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepo.findAll();

        return mapToProductResponse(products);
    }

    private static List<ProductResponse> mapToProductResponse(List<Product> products) {
        return products.stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
