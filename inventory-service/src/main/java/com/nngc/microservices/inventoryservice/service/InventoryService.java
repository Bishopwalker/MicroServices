package com.nngc.microservices.inventoryservice.service;

import com.nngc.microservices.inventoryservice.dto.InventoryResponse;
import com.nngc.microservices.inventoryservice.repo.InventoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import lombok.SneakyThrows;
import lombok.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepo inventoryRepo;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode){
        log.info("Checking inventory for {}", skuCode);
       return inventoryRepo.findBySkuCodeIn(skuCode).stream()
               .map(inventory-> InventoryResponse.builder()
                       .skuCode(inventory.getSkuCode())
                       .isInStock(inventory.getQuantity() > 0)
                       .build())
               .toList();



    }
}
