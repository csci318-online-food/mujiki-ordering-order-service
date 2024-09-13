package com.csci318.microservice.orderservice.Services.Impl;

import com.csci318.microservice.orderservice.DTOs.OrderDTORequest;
import com.csci318.microservice.orderservice.DTOs.OrderDTOResponse;
import com.csci318.microservice.orderservice.Entities.Order;
import com.csci318.microservice.orderservice.Entities.OrderItem;
import com.csci318.microservice.orderservice.Mappers.OrderMapper;
import com.csci318.microservice.orderservice.Repositories.OrderItemRepository;
import com.csci318.microservice.orderservice.Repositories.OrderRepository;
import com.csci318.microservice.orderservice.Services.IOrderService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDTOResponse findById(UUID id) {
        Order order = this.orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }
        return this.orderMapper.toDtos(order);
    }

    // NOTE: user and restaurant will be resolved by the gateway service
    @Override
    public OrderDTOResponse createOrder(OrderDTORequest orderDTO) {
        try {
            Order order = new Order();
            order.setId(orderDTO.getId());
            order.setUserId(orderDTO.getUserId());
            order.setRestaurantId(orderDTO.getRestaurantId());
            order.setTotalPrice(orderDTO.getTotalPrice());
            order.setStatus(orderDTO.getStatus());
            order.setOrderTime(orderDTO.getOrderTime());
            this.orderRepository.save(order);
            return this.orderMapper.toDtos(order);
        } catch (Exception e) {
            log.error("Failed to create order", e);
            throw new RuntimeException("Failed to create order", e);
        }
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return this.orderItemRepository.save(orderItem);
    }

}
