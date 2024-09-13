package com.csci318.microservice.orderservice.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    private UUID id;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "restaurant_id")
    private UUID restaurantId;

    @Column(name = "item_id")
    private UUID itemId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private Double price; // Price of the item at the time of adding to the cart
}
