package com.csci318.microservice.orderservice.Mappers;

import com.csci318.microservice.orderservice.DTOs.OrderDTORequest;
import com.csci318.microservice.orderservice.DTOs.OrderDTOResponse;
import com.csci318.microservice.orderservice.Entities.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper implements Mapper<Order, OrderDTOResponse, OrderDTORequest> {
    @Override
    public OrderDTOResponse toDtos(Order entity) {
        OrderDTOResponse dto = new OrderDTOResponse();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setRestaurantId(entity.getRestaurantId());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setStatus(entity.getStatus());
        dto.setOrderTime(entity.getOrderTime());
        return dto;

    }

    @Override
    public Order toEntities(OrderDTORequest dto) {
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setUserId(dto.getUserId());
        entity.setRestaurantId(dto.getRestaurantId());
        entity.setTotalPrice(dto.getTotalPrice());
        entity.setStatus(dto.getStatus());
        entity.setOrderTime(dto.getOrderTime());
        return entity;
    }

    @Override
    public List<OrderDTOResponse> toDtos(List<Order> orders) {
        return orders.stream().map(this::toDtos).toList();
    }

    @Override
    public List<Order> toEntities(List<OrderDTORequest> dtos) {
        return dtos.stream().map(this::toEntities).toList();
    }
}
