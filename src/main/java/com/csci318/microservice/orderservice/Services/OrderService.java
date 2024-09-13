package com.csci318.microservice.orderservice.Services;

import com.csci318.microservice.orderservice.DTOs.OrderDTORequest;
import com.csci318.microservice.orderservice.DTOs.OrderDTOResponse;
import com.csci318.microservice.orderservice.DTOs.OrderItemDTORequest;
import com.csci318.microservice.orderservice.DTOs.OrderItemDTOResponse;
import com.csci318.microservice.orderservice.Entities.OrderItem;

import java.util.UUID;

public interface OrderService {

    OrderDTOResponse findById(UUID id);
    OrderDTOResponse createOrder(OrderDTORequest orderDTO);
    OrderItemDTOResponse addOrderItem(UUID orderId, OrderItemDTORequest orderItem);
}
