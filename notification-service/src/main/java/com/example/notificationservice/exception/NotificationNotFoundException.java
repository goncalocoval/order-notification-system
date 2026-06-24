package com.example.notificationservice.exception;

public class NotificationNotFoundException extends RuntimeException {
    public NotificationNotFoundException(Long orderId) {
        super("No notifications found for order id " + orderId);
    }
}
