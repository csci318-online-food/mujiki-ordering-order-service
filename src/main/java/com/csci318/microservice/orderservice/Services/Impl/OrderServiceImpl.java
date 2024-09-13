package com.csci318.microservice.orderservice.Services.Impl;

import com.csci318.microservice.orderservice.DTOs.OrderDTORequest;
import com.csci318.microservice.orderservice.DTOs.OrderDTOResponse;
import com.csci318.microservice.orderservice.DTOs.OrderItemDTORequest;
import com.csci318.microservice.orderservice.DTOs.OrderItemDTOResponse;
import com.csci318.microservice.orderservice.Entities.Order;
import com.csci318.microservice.orderservice.Entities.OrderItem;
import com.csci318.microservice.orderservice.Mappers.OrderItemMapper;
import com.csci318.microservice.orderservice.Mappers.OrderMapper;
import com.csci318.microservice.orderservice.Repositories.OrderItemRepository;
import com.csci318.microservice.orderservice.Repositories.OrderRepository;
import com.csci318.microservice.orderservice.Services.OrderService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            OrderMapper orderMapper, OrderItemMapper orderItemMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
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
    public OrderItemDTOResponse addOrderItem(UUID orderId, OrderItemDTORequest orderItem) {
        try {
            OrderItem item = new OrderItem();
            item.setId(orderItem.getId());
            item.setOrderId(orderId);
            item.setRestaurantId(orderItem.getRestaurantId());
            item.setItemId(orderItem.getItemId());
            item.setQuantity(orderItem.getQuantity());
            item.setPrice(orderItem.getPrice());
            this.orderItemRepository.save(item);
            return this.orderItemMapper.toDtos(item);
        } catch (Exception e) {
            log.error("Failed to create order item", e);
            throw new RuntimeException("Failed to create order item", e);
        }
    }

}
