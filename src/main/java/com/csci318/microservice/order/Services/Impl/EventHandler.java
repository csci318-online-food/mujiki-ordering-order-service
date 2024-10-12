package com.csci318.microservice.order.Services.Impl;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.csci318.microservice.order.Domain.Events.OrderStatusChangedEvent;
import com.csci318.microservice.order.Repositories.OrderEventRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventHandler {
    private final OrderEventRepository orderEventRepository;
    private final StreamBridge streamBridge;

    public EventHandler(OrderEventRepository orderEventRepository, StreamBridge streamBridge) {
        this.orderEventRepository = orderEventRepository;
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void handleOrderStatusChangedEvent(OrderStatusChangedEvent event) {
        try {
            // STEP 1: save the event to the database
            orderEventRepository.save(event);

            // STEP 2: push the event to relevant channels
            streamBridge.send("mujikiOrderEventChannel", event);
        } catch (Exception e) {
            log.error("Error handling OrderStatusChangedEvent: ", e);
        }
    }
}
