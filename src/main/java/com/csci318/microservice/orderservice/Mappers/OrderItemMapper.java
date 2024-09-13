package com.csci318.microservice.orderservice.Mappers;

import com.csci318.microservice.orderservice.DTOs.OrderItemDTORequest;
import com.csci318.microservice.orderservice.DTOs.OrderItemDTOResponse;
import com.csci318.microservice.orderservice.Entities.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemMapper implements Mapper<OrderItem, OrderItemDTOResponse, OrderItemDTORequest> {

    @Override
    public OrderItemDTOResponse toDtos(OrderItem entity) {
        OrderItemDTOResponse dto = new OrderItemDTOResponse();
        dto.setId(entity.getId());
        dto.setOrderId(entity.getOrderId());
        dto.setRestaurantId(entity.getRestaurantId());
        dto.setItemId(entity.getItemId());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    @Override
    public OrderItem toEntities(OrderItemDTORequest dto) {
        OrderItem entity = new OrderItem();
        entity.setId(dto.getId());
        entity.setOrderId(dto.getOrderId());
        entity.setRestaurantId(dto.getRestaurantId());
        entity.setItemId(dto.getItemId());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    @Override
    public List<OrderItemDTOResponse> toDtos(List<OrderItem> orderItems) {
        return orderItems.stream().map(this::toDtos).toList();
    }

    @Override
    public List<OrderItem> toEntities(List<OrderItemDTORequest> dtos) {
        return dtos.stream().map(this::toEntities).toList();
    }
}
