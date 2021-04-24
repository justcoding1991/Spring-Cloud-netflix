package com.springcloud.learn.example.orderserviceprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigController {

    //Environment get the indicate configuration
    @Value("${hello}")
    private String txt;

    @GetMapping("/config")
    public String config(){
        return txt + " hello";
    }
}
