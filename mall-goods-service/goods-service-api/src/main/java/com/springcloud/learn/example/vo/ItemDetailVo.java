package com.springcloud.learn.example.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ItemDetailVo {
    private Long itemId;

    private String itemName;

    private Double price;

    private String title;

    private String sellPoint;

    private String image;

    private Date updateTime;
    private int num;
}
