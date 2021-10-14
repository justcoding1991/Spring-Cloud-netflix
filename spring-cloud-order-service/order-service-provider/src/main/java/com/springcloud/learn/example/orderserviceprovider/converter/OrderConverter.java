package com.springcloud.learn.example.orderserviceprovider.converter;

import com.springcloud.learn.example.dto.ItemStockDto;
import com.springcloud.learn.example.orderserviceprovider.dto.ItemDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderConverter {

    ItemStockDto itemDto2StockDto(ItemStockDto itemStockDto);

    List<ItemStockDto> itemDto2StockDtoList(List<ItemDto> itemDtos);
}
