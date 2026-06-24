package com.example.notificationservice.dto;

import java.time.LocalDateTime;

public record NotificationResponse(
        Long id,
        Long orderId,
        String customerName,
        String customerEmail,
        String product,
        String status,
        String message,
        LocalDateTime createdAt
) {}
