package com.nngc.microservices.orderservices.controller;

import com.nngc.microservices.orderservices.dto.OrderRequest;
import com.nngc.microservices.orderservices.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        log.warn("Order Request: {}", orderRequest);
        orderService.placeOrder(orderRequest);

        //build a response with stringBilder to return the items in order
//return the response
        StringBuilder response = new StringBuilder();
        orderRequest.getOrderLineItemsDto().forEach(orderLineItemsDto -> {
            response.append("Order Line Item: ").append(orderLineItemsDto.getSkuCode())
                    .append(" Price: ").append(orderLineItemsDto.getPrice())
                    .append(" Quantity: ").append(orderLineItemsDto.getQuantity())
                    .append("\n");
        });

        return response.toString();
    }
}
