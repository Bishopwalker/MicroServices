package com.nngc.microservices.inventoryservice.controller;

import com.nngc.microservices.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.nngc.microservices.inventoryservice.dto.InventoryResponse;

import java.util.List;


@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {


    private final InventoryService service;
    @GetMapping("/isInStock")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return service.isInStock(skuCode);
    }
}
