package com.csci318.microservice.orderservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTOResponse {
    private UUID id;
    private UUID userId;
    private UUID restaurantId;
    private Double totalPrice;
    private String status;
    private Timestamp orderTime;
}
