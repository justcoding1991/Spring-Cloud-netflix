package com.springcloud.learn.goods.goodsserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = "com.springcloud.learn.goods.goodsserviceprovider.*")
@EnableTransactionManagement
@EnableFeignClients
@SpringBootApplication
public class GoodsServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsServiceProviderApplication.class, args);
    }

}
