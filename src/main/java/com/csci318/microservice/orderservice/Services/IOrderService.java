package com.csci318.microservice.orderservice.Services;

import com.csci318.microservice.orderservice.DTOs.OrderDTORequest;
import com.csci318.microservice.orderservice.DTOs.OrderDTOResponse;

public interface IOrderService {

    OrderDTOResponse createOrder(OrderDTORequest orderDTO);
}
