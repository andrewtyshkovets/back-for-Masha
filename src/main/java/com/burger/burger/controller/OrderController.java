package com.burger.burger.controller;

import com.burger.burger.model.OrderDto;
import com.burger.burger.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(value = "*")
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

    @PutMapping("/orders/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ValidateOnExecution
    public ResponseEntity<OrderDto> saveOrder(@Valid @RequestBody OrderDto orderDto, @PathVariable UUID id) {
        return new ResponseEntity(orderService.updateOrder(orderDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ValidateOnExecution
    public void deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrder(id);
    }
}
