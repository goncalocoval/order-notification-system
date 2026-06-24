package com.example.orderservice.event;

import com.example.orderservice.model.OrderStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderEvent implements Serializable {

    private Long orderId;
    private String customerName;
    private String customerEmail;
    private String product;
    private OrderStatus status;
    private LocalDateTime timestamp;

    public OrderEvent() {}

    public OrderEvent(Long orderId, String customerName, String customerEmail, String product, OrderStatus status){
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.product = product;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public Long getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public String getProduct() { return product; }
    public OrderStatus getStatus() { return status; }
    public LocalDateTime getTimestamp() { return timestamp; }

}
