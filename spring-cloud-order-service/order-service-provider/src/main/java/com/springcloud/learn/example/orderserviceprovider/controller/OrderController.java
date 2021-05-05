package com.springcloud.learn.example.orderserviceprovider.controller;

import com.springcloud.learn.example.api.R;
import com.springcloud.learn.example.orderserviceprovider.dto.OrderDto;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @PostMapping("/order/add")
    public R addOrder(@RequestBody @Validated OrderDto orderDto, BindingResult bindingResult){
        orderDto.validData(bindingResult);
        return new R.Builder<>().buildOk();
    }

}
