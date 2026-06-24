package com.example.notificationservice.service;

import com.example.notificationservice.dto.NotificationResponse;
import com.example.notificationservice.event.OrderEvent;
import com.example.notificationservice.model.Notification;
import com.example.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    public void processEvent(OrderEvent event){
        String message = String.format(
                "Order #%d for %s has been %s.",
                event.getOrderId(),
                event.getProduct(),
                event.getStatus()
        );

        Notification notification = new Notification(
                event.getOrderId(),
                event.getCustomerName(),
                event.getCustomerEmail(),
                event.getProduct(),
                event.getStatus(),
                message
        );

        notificationRepository.save(notification);
    }

    public List<NotificationResponse> getAll(){
        return notificationRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<NotificationResponse> getByOrderId(Long orderId){
        return notificationRepository.findByOrderId(orderId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private NotificationResponse toResponse(Notification notification){
        return new NotificationResponse(
                notification.getId(),
                notification.getOrderId(),
                notification.getCustomerName(),
                notification.getCustomerEmail(),
                notification.getProduct(),
                notification.getStatus(),
                notification.getMessage(),
                notification.getCreatedAt()
        );
    }

}
