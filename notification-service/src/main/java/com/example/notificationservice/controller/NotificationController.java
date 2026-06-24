package com.example.notificationservice.controller;

import com.example.notificationservice.dto.NotificationResponse;
import com.example.notificationservice.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Notifications", description = "Endpoint for retrieving notifications")
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @Operation(summary = "Get all notifications", description = "Returns a list of all notifications")
    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAll(){
        return ResponseEntity.ok(notificationService.getAll());
    }

    @Operation(summary = "Get notifications by order ID", description = "Returns all notifications for a specific order")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<NotificationResponse>> getByOrderId(@PathVariable Long orderId){
        return ResponseEntity.ok(notificationService.getByOrderId(orderId));
    }

}
