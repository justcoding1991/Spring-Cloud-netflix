package com.springcloud.learn.example.orderserviceprovider;

import com.springcloud.learn.example.OrderService;
import com.springcloud.learn.example.dto.OrderDto;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderServiceImpl implements OrderService {

    @Override
    public String order() {
        try{
//            Thread.sleep(5000);
        }catch(Exception e){
            System.out.println(e.toString());
        }

        return "return all orders";
    }

    @Override
    public int insert(OrderDto dto) {
        return 0;
    }
}
