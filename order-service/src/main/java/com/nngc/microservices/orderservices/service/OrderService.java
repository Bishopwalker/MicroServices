package com.nngc.microservices.orderservices.service;


import com.nngc.microservices.orderservices.dto.OrderLineItemsDto;
import com.nngc.microservices.orderservices.dto.OrderRequest;
import com.nngc.microservices.orderservices.entity.Order;
import com.nngc.microservices.orderservices.entity.OrderLineItems;
import com.nngc.microservices.orderservices.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import com.nngc.microservices.orderservices.dto.InventoryResponse;
import java.util.List;
import java.util.UUID;
import java.util.Arrays;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class OrderService {

    private final OrderRepo orderRepo;
    private final WebClient.Builder webClientBuilder;


    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        if(orderRequest.getOrderLineItemsDto() == null){
            throw new IllegalArgumentException("Order Line Items cannot be empty");
        }
log.info(orderRequest.getOrderLineItemsDto().toString());
        // map to dto
        // map to entity (OrderLineItems
     List<OrderLineItems> lineItems =  orderRequest.getOrderLineItemsDto().stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItems(lineItems);

       List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();
        //Call inventory service and place order if product is in stock
      InventoryResponse[] result=  webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/api/inventory/isInStock",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

             boolean allProductsInStock = Arrays.stream(result).allMatch(InventoryResponse::isInStock);

              if(allProductsInStock){
                  orderRepo.save(order);
              }else{
                  throw new IllegalArgumentException("Product is out of stock");
              }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
