package com.example.orderservice.dto;

import com.example.orderservice.model.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderStatusRequest(
        @NotNull(message = "Status cannot be null")
        OrderStatus status
) {}