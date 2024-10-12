package com.csci318.microservice.order.Services.Impl;

import com.csci318.microservice.order.Constants.OrderStatus;
import com.csci318.microservice.order.DTOs.OrderDTORequest;
import com.csci318.microservice.order.DTOs.OrderDTOResponse;
import com.csci318.microservice.order.DTOs.OrderItemDTORequest;
import com.csci318.microservice.order.DTOs.OrderItemDTOResponse;
import com.csci318.microservice.order.Domain.Entities.Order;
import com.csci318.microservice.order.Domain.Entities.OrderItem;
import com.csci318.microservice.order.Domain.Events.OrderStatusChangedEvent;
import com.csci318.microservice.order.Mappers.Impl.OrderItemMapper;
import com.csci318.microservice.order.Mappers.Impl.OrderMapper;
import com.csci318.microservice.order.Repositories.OrderItemRepository;
import com.csci318.microservice.order.Repositories.OrderRepository;
import com.csci318.microservice.order.Services.OrderService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

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

            OrderStatusChangedEvent event = new OrderStatusChangedEvent();
            event.setEventName("Order created");
            event.setOrderId(order.getId());
            event.setUserId(order.getUserId());
            event.setRestaurantId(order.getRestaurantId());
            event.setOldStatus(null);
            event.setStatus(order.getStatus());
            event.setChangeTime(LocalDateTime.now());
            eventPublisher.publishEvent(event);

            return this.orderMapper.toDtos(order);

        } catch (Exception e) {
            log.error("Failed to create order", e);
            throw new RuntimeException("Failed to create order", e);
        }
    }

    @Override
    public OrderDTOResponse updateOrderStatus(UUID id, OrderStatus orderStatus) {
        Order order = this.orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        try {
            OrderStatus oldStatus = order.updateStatus(orderStatus);
            this.orderRepository.save(order);

            log.info(
                "Updated order " + order.getId().toString() +
                " from " + oldStatus.toString() +
                " to " + orderStatus.toString() + "."
            );

            OrderStatusChangedEvent event = new OrderStatusChangedEvent();
            event.setEventName("Order created");
            event.setOrderId(order.getId());
            event.setUserId(order.getUserId());
            event.setRestaurantId(order.getRestaurantId());
            event.setOldStatus(oldStatus);
            event.setStatus(order.getStatus());
            event.setChangeTime(LocalDateTime.now());
            eventPublisher.publishEvent(event);

            return this.orderMapper.toDtos(order);
        } catch (Exception e) {
            log.error("Failed to update order status", e);
            throw new RuntimeException("Faield to update order status", e);
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
