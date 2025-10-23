package com.accenture.product.controller;

import com.accenture.product.dto.OrderDTO;
import com.accenture.product.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public OrderDTO placeOrder(@RequestParam String name, @RequestParam(required = false, defaultValue = "1") Integer qty) {
        return orderService.placeOrder(name, qty);
    }
}
