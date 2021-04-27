package com.springcloud.learn.example.userserviceprovider.controller;

import com.springcloud.learn.example.userserviceprovider.hystrix.MyHystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyHystrixController {

    @Autowired
    RestTemplate restTemplate;


    @MyHystrixCommand(fallback = "fallback",timeout = 3000)
    @GetMapping("/hystrix/mytest")
    public String test(){
        return restTemplate.getForObject("http://localhost:8082/orders",String.class);
    }

    public String fallback(){
        return "请求被降级";
    }
}
