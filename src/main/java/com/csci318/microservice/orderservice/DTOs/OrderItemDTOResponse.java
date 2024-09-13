package com.csci318.microservice.orderservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDTOResponse {
    private UUID id;
    private UUID orderId;
    private UUID restaurantId;
    private UUID itemId;
    private int quantity;
    private Double price;
}
