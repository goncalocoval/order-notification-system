package com.example.orderservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record OrderRequest(
        @NotBlank(message = "Customer name cannot be blank")
        String customerName,

        @NotBlank(message = "Customer email cannot be blank")
        @Email(message = "Invalid email format")
        String customerEmail,

        @NotBlank(message = "Product cannot be blank")
        String product,

        @Min(value = 1, message = "Quantity must be at least 1")
        int quantity
) {}
