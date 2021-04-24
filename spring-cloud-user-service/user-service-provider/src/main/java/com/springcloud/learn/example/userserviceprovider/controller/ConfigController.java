package com.springcloud.learn.example.userserviceprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/user")
public class ConfigController {

    @Value("${env}")
    private String env;

    @GetMapping("/test")
    public String getEnv(){
        return "env: " + env;
    }

}
