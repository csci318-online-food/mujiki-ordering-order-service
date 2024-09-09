package com.csci318.microservice.orderservice.Services.Impl;

import com.csci318.microservice.orderservice.DTOs.OrderDTORequest;
import com.csci318.microservice.orderservice.DTOs.OrderDTOResponse;
import com.csci318.microservice.orderservice.Entities.Order;
import com.csci318.microservice.orderservice.Mappers.OrderMapper;
import com.csci318.microservice.orderservice.Repositories.OrderRepository;
import com.csci318.microservice.orderservice.Services.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    // NOTE: user and restaurant will be resolved by the gateway service
    public OrderDTOResponse createOrder(OrderDTORequest orderDTO) {
        try {
            Order order = new Order();
            order.setUserId(orderDTO.getUserId());
            order.setRestaurantId(orderDTO.getRestaurantId());
            order.setTotalPrice(orderDTO.getTotalPrice());
            order.setStatus(orderDTO.getStatus());
            order.setOrderTime(orderDTO.getOrderTime());
            this.orderRepository.save(order);
            return this.orderMapper.toDtos(order);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create order");
        }
    }
}
