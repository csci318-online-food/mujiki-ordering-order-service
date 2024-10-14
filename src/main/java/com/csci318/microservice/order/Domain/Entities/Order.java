package com.csci318.microservice.order.Domain.Entities;

import com.csci318.microservice.order.Constants.OrderStatus;
import com.csci318.microservice.order.Domain.Relations.Restaurant;
import com.csci318.microservice.order.Utils.Annotations.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.catalina.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    @ManyToOne(targetEntity = User.class)
    private UUID userId;

    @Column(name = "restaurant_id", nullable = false)
    @ManyToOne(targetEntity = Restaurant.class)
    private UUID restaurantId;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // "CONFIRMED", "CANCELLED", "COMPLETED"

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Column(name = "create_at")
    private Timestamp createAt;

    @Column(name = "modify_at")
    private Timestamp modifyAt;

    @Column(name = "modify_by", length = 64)
    private String modifyBy;

    @Column(name = "create_by", length = 64)
    private String createBy;

    // Tries to set the order status to newStatus.
    // Throws if the update is invalid.
    // Otherwise, returns the old status.
    public OrderStatus updateStatus(OrderStatus newStatus) {
        OrderStatus oldStatus = this.status;

        if (newStatus == OrderStatus.CANCELLED) {
            if (oldStatus == OrderStatus.COMPLETED) {
                throw new RuntimeException("Cannot cancel a completed order.");
            }
        } else {
            if (newStatus.compareTo(oldStatus) <= 0) {
                throw new RuntimeException("Cannot proceed to an earlier stage.");
            }
        }

        setStatus(newStatus);

        return oldStatus;
    }
}
