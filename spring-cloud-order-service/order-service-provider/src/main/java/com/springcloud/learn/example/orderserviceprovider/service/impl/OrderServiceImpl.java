package com.springcloud.learn.example.orderserviceprovider.service.impl;

import com.springcloud.learn.example.api.R;
import com.springcloud.learn.example.client.IGoodFeignClient;
import com.springcloud.learn.example.exception.BizException;
import com.springcloud.learn.example.orderserviceprovider.converter.OrderConverter;
import com.springcloud.learn.example.orderserviceprovider.dto.ItemDto;
import com.springcloud.learn.example.orderserviceprovider.dto.OrderDto;
import com.springcloud.learn.example.orderserviceprovider.mapper.entity.TbOrder;
import com.springcloud.learn.example.orderserviceprovider.mapper.entity.TbOrderItem;
import com.springcloud.learn.example.orderserviceprovider.mapper.persistence.TbOrderItemMapper;
import com.springcloud.learn.example.orderserviceprovider.mapper.persistence.TbOrderMapper;
import com.springcloud.learn.example.orderserviceprovider.service.IOrderService;
import com.springcloud.learn.example.vo.ItemDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class OrderServiceImpl implements IOrderService {


    @Autowired
    IGoodFeignClient goodFeignClient;

    @Autowired
    TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    TbOrderMapper tbOrderMapper;

    @Autowired
    OrderConverter orderConverter;

    @Override
    public String createOrder(OrderDto orderDto) {
        /**
         * 1,锁库存
         * 2.查询商品信息
         * 3.创建订单
         */
        R r = goodFeignClient.decreaseStock(orderConverter.itemDto2StockDtoList(orderDto.getItems()));
        if (r.getCode()!=200){
            throw new BizException(r.getMsg());
        }
        List<Long> ids = orderDto.getItems().stream().map(dto->Long.parseLong(dto.getItemId())).collect(Collectors.toList());
        R<List<ItemDetailVo>> listR = goodFeignClient.getItemsByIds(ids);
        BigDecimal totalPrice = new BigDecimal(0);
//        String orderId = UUID.randomUUID().toString().replace("-","");
        long newOrderId = new Date().getTime();
        int i=0;
        for(ItemDto itemDto:orderDto.getItems()){
            for (ItemDetailVo itemDetailVo:listR.getData()){
                if(itemDetailVo.getItemId().toString().equals(itemDto.getItemId())){
                    BigDecimal totalFee = BigDecimal.valueOf(itemDetailVo.getPrice()).multiply(BigDecimal.valueOf(itemDto.getNum()));
                    totalPrice = totalPrice.add(totalFee);
                    TbOrderItem tbOrderItem = new TbOrderItem();
                    tbOrderItem.setItemId(itemDetailVo.getItemId());
                    tbOrderItem.setNum(itemDetailVo.getNum());
                    tbOrderItem.setCreateTime(new Date());
                    tbOrderItem.setOrderId(newOrderId);
                    tbOrderItem.setPrice(itemDetailVo.getPrice());
                    tbOrderItem.setTotalFee(String.valueOf(totalFee));
                    tbOrderItem.setUpdateTime(new Date());
                    tbOrderItem.setStatus(1);
                    tbOrderItem.setId(Long.parseLong(Long.toString(newOrderId) + i++));
                    tbOrderItemMapper.insert(tbOrderItem);
                }
            }
        }
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderId(newOrderId);
        tbOrder.setPayment(String.valueOf(totalPrice));
        tbOrder.setStatus("0");
        tbOrder.setCreateTime(new Date());
        tbOrder.setUserId(10000001);
        tbOrder.toString();
        tbOrderMapper.insert(tbOrder);
        return Long.toString(newOrderId);
    }
}
