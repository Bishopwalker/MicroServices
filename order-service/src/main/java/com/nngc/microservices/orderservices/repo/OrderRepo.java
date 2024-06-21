package com.nngc.microservices.orderservices.repo;

import com.nngc.microservices.orderservices.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
