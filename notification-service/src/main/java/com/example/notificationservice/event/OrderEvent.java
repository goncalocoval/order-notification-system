package com.example.notificationservice.event;

import java.time.LocalDateTime;

public class OrderEvent {

    private Long orderId;
    private String customerName;
    private String customerEmail;
    private String product;
    private String status;
    private LocalDateTime timestamp;

    public OrderEvent() {}

    public Long getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public String getProduct() { return product; }
    public String getStatus() { return status; }
    public LocalDateTime getTimestamp() { return timestamp; }

}
