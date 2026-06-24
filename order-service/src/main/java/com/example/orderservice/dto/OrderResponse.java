package com.example.orderservice.dto;

import com.example.orderservice.model.OrderStatus;

import java.time.LocalDateTime;

public record OrderResponse(
        Long id,
        String customerName,
        String customerEmail,
        String product,
        int quantity,
        OrderStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}