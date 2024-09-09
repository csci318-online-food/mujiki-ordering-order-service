package com.csci318.microservice.orderservice.Repositories;

import com.csci318.microservice.orderservice.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
