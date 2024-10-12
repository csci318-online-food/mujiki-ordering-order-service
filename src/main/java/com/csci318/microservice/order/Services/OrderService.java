package com.csci318.microservice.order.Services;

import com.csci318.microservice.order.Constants.OrderStatus;
import com.csci318.microservice.order.DTOs.OrderDTORequest;
import com.csci318.microservice.order.DTOs.OrderDTOResponse;
import com.csci318.microservice.order.DTOs.OrderItemDTORequest;
import com.csci318.microservice.order.DTOs.OrderItemDTOResponse;

import java.util.UUID;

public interface OrderService {
    OrderDTOResponse findById(UUID id);
    OrderDTOResponse createOrder(OrderDTORequest orderDTO);
    OrderDTOResponse updateOrderStatus(UUID id, OrderStatus orderStatus);
    OrderItemDTOResponse addOrderItem(UUID orderId, OrderItemDTORequest orderItem);
}
