package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.dto.OrderStatusRequest;
import com.example.orderservice.event.OrderEvent;
import com.example.orderservice.exception.OrderNotFoundException;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    public OrderService(OrderRepository orderRepository, RabbitTemplate rabbitTemplate){
        this.orderRepository = orderRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public OrderResponse create(OrderRequest request){
        Order order = new Order(
                request.customerName(),
                request.customerEmail(),
                request.product(),
                request.quantity()
        );
        orderRepository.save(order);
        publishEvent(order);
        return toResponse(order);
    }

    public List<OrderResponse> getAll(){
        return orderRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public OrderResponse getById(Long id){
        return toResponse(orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id)));
    }

    @Transactional
    public OrderResponse updateStatus(Long id, OrderStatusRequest request){
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        order.setStatus(request.status());
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        publishEvent(order);
        return toResponse(order);
    }

    private void publishEvent(Order order){
        OrderEvent event = new OrderEvent(
                order.getId(),
                order.getCustomerName(),
                order.getCustomerEmail(),
                order.getProduct(),
                order.getStatus()
        );
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
    }

    private OrderResponse toResponse(Order order){
        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                order.getCustomerEmail(),
                order.getProduct(),
                order.getQuantity(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }

}
