package com.example.Mani.s.Dam.Biryani.controller;

import com.example.Mani.s.Dam.Biryani.model.BillResponse;
import com.example.Mani.s.Dam.Biryani.model.OrderIdEntity;
import com.example.Mani.s.Dam.Biryani.model.OrderRequest;
import com.example.Mani.s.Dam.Biryani.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/Order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orderItem")
    public OrderIdEntity orderItem(@RequestBody @Valid OrderRequest orderRequest){ //TODO Replace Entity with Request
        return orderService.orderItem(orderRequest);
    }
    @GetMapping("/bill/{orderId}")
    public BillResponse bill(@PathVariable Integer orderId){
        return orderService.getBill(orderId);
    }

    @PutMapping("/addItemQuantity/")
        public void addItemQuantity(@RequestBody OrderIdEntity orderIdEntity){
        orderService.addItemQuantity(orderIdEntity);
        }
    }

