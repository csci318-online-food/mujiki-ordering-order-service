package com.csci318.microservice.orderservice.Application;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import com.csci318.microservice.orderservice.shareddomain.OrderStatusEvent;

@Service
public class OrderEventPublisherService {
    private final StreamBridge streamBridge;

    public OrderEventPublisherService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @TransactionalEventListener
    public void handleOrderStatusEvent(OrderStatusEvent orderStatusEvent) {
        streamBridge.send("orderStatusChannel", orderStatusEvent);
    }
}
