package com.csci318.microservice.orderservice.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTORequest {
    private UUID id;
    private UUID userId;
    private UUID restaurantId;
    private Double totalPrice;
    private String status;
    private LocalDateTime orderTime;
}
