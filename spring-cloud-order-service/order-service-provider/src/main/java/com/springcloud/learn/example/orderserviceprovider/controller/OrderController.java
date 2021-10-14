package com.springcloud.learn.example.orderserviceprovider.controller;

import com.springcloud.learn.example.api.R;
import com.springcloud.learn.example.orderserviceprovider.dto.OrderDto;
import com.springcloud.learn.example.orderserviceprovider.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    IOrderService orderService;

    @PostMapping("/order/add")
    public R addOrder(@RequestBody @Validated OrderDto orderDto, BindingResult bindingResult){
        orderDto.validData(bindingResult);
        String orderId = orderService.createOrder(orderDto);
        return new R.Builder<>().setData(orderId).buildOk();
    }

}
