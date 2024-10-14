package com.csci318.microservice.order.Domain.Entities;

import com.csci318.microservice.order.Domain.Relations.Item;
import com.csci318.microservice.order.Domain.Relations.Restaurant;
import com.csci318.microservice.order.Utils.Annotations.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    @ManyToOne(targetEntity = Order.class)
    private UUID orderId;

    @Column(name = "restaurant_id")
    @ManyToOne(targetEntity = Restaurant.class)
    private UUID restaurantId;

    @Column(name = "item_id")
    @ManyToOne(targetEntity = Item.class)
    private UUID itemId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private Double price; // Price of the item at the time of adding to the cart
}
