package com.csci318.microservice.orderservice.Domain.Relations;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {
    private UUID id;
    private UUID restaurantId;
    private String name;
    private String description;
    private Double price;
    private boolean availability;
}
