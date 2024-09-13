package com.csci318.microservice.orderservice.Controllers;

import com.csci318.microservice.orderservice.DTOs.OrderDTORequest;
import com.csci318.microservice.orderservice.DTOs.OrderDTOResponse;
import com.csci318.microservice.orderservice.DTOs.OrderItemDTORequest;
import com.csci318.microservice.orderservice.DTOs.OrderItemDTOResponse;
import com.csci318.microservice.orderservice.Entities.OrderItem;
import com.csci318.microservice.orderservice.Services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTOResponse> getOrder(@PathVariable UUID id) {
        OrderDTOResponse orderResponse = orderService.findById(id);
        return ResponseEntity.ok(orderResponse);
    }

    @PostMapping("/create-order")
    public ResponseEntity<OrderDTOResponse> createOrder(@RequestBody OrderDTORequest orderRequest) {
        OrderDTOResponse orderResponse = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(orderResponse);
    }

    @PostMapping("/{id}/add-order-item")
    public ResponseEntity<OrderItemDTOResponse> addOrderItem(@PathVariable UUID id, @RequestBody OrderItemDTORequest orderRequest) {
        OrderItemDTOResponse orderResponse = orderService.addOrderItem(id, orderRequest);
        return ResponseEntity.ok(orderResponse);
    }
}
