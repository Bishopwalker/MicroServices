package com.nngc.microservices.inventoryservice.repo;

import com.nngc.microservices.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InventoryRepo extends JpaRepository<Inventory, Long>{

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
