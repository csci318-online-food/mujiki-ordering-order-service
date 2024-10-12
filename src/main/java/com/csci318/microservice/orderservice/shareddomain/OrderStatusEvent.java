package com.csci318.microservice.orderservice.shareddomain;

public class OrderStatusEvent {
  OrderStatusEventData orderStatusEventData;
  public OrderStatusEvent(){}
  public OrderStatusEvent(OrderStatusEventData orderStatusEventData){
      this.orderStatusEventData = orderStatusEventData;
  }

  public void setOrderStatusEventData(OrderStatusEventData orderStatusEventData){this.orderStatusEventData = orderStatusEventData;}
  public OrderStatusEventData getOrderStatusEventData(){
      return orderStatusEventData;
  }

  @Override
  public String toString() {
      return "OrderStatusEvent{" +
              "orderStatusEventData=" + orderStatusEventData +
              '}';
  }
}
