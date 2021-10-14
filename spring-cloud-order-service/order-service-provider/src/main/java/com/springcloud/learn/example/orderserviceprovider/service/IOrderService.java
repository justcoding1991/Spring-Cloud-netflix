package com.springcloud.learn.example.orderserviceprovider.service;


import com.springcloud.learn.example.orderserviceprovider.dto.OrderDto;

public interface IOrderService {

    public String createOrder(OrderDto orderDto);
}
