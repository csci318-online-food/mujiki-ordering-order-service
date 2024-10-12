package com.csci318.microservice.order.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import com.csci318.microservice.order.Constants.OrderStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTOResponse {
    private UUID id;
    private UUID userId;
    private UUID restaurantId;
    private Double totalPrice;
    private OrderStatus status;
    private LocalDateTime orderTime;
}
