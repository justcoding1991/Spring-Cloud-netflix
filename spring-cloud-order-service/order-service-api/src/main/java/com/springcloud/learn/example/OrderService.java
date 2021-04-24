package com.springcloud.learn.example;

import com.springcloud.learn.example.dto.OrderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface OrderService {

    @GetMapping("/orders")
    String order();

    @PostMapping("/addOrder")
    int insert(OrderDto dto);
}
