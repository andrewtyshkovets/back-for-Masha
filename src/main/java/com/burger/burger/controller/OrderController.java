package com.burger.burger.controller;

import com.burger.burger.model.OrderDto;
import com.burger.burger.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;
import javax.websocket.OnError;
import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<OrderDto>> getOrders() {
        return new ResponseEntity(orderService.getOrders(), HttpStatus.OK);
    }

    @PostMapping("/orders")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ValidateOnExecution
    public ResponseEntity<OrderDto> saveOrder(@Valid @RequestBody OrderDto orderDto) {
        return new ResponseEntity(orderService.saveOrder(orderDto), HttpStatus.OK);
    }
}
