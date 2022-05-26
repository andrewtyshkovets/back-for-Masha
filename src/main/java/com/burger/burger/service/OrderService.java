package com.burger.burger.service;

import com.burger.burger.model.Order;
import com.burger.burger.model.OrderDto;
import com.burger.burger.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDto saveOrder(OrderDto orderDto) {
        Order order = orderMapper.orderDtoToOrder(orderDto);
        order.setOrderProducts(null);
        orderRepository.save(order);
        order = orderMapper.updateOrderFromDto(orderDto, order);
        Order finalOrder = order;
        order.setOrderProducts(order.getOrderProducts().stream().peek(orderProducts ->
                orderProducts.setOrderId(finalOrder.getId())).collect(Collectors.toList()));
        orderRepository.save(order);
        return orderMapper.orderToOrderDto(order);
    }

    public Order getOrderById(UUID uuid) {
        return orderRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<OrderDto> getOrders() {
        return orderRepository.findAll().stream().map(orderMapper::orderToOrderDto).collect(Collectors.toList());
    }

    public OrderDto updateOrder(OrderDto orderDto, UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order = orderMapper.updateOrderFromDto(orderDto, order);
        return orderMapper.orderToOrderDto(orderRepository.save(order));
    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }
}
