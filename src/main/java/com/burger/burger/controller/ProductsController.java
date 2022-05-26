package com.burger.burger.controller;

import com.burger.burger.model.Product;
import com.burger.burger.service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/ingredients")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Product>> getOrders() {
        return new ResponseEntity(productsService.getAllProducts(), HttpStatus.OK);
    }
}
