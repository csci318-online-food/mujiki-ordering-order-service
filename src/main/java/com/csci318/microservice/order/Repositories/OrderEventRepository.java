package com.csci318.microservice.order.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csci318.microservice.order.Domain.Events.OrderStatusChangedEvent;

import java.util.UUID;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderStatusChangedEvent, UUID> {

}
