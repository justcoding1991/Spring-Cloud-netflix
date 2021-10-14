package com.springcloud.learn.goods.goodsserviceprovider.converter;


import com.springcloud.learn.example.dto.ItemStockDto;
import com.springcloud.learn.example.vo.ItemDetailVo;
import com.springcloud.learn.goods.goodsserviceprovider.domain.ItemStockDo;
import com.springcloud.learn.goods.goodsserviceprovider.mapper.entity.TbItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel="spring")
public interface ItemConverter {

    @Mappings({})
    ItemDetailVo tbItemDetail2Vo(TbItem tbItem);

    List<ItemDetailVo> itemDetail2VoList(List<TbItem> tbItemList);

    @Mappings({})
    ItemStockDo itemStockDto2Do(ItemStockDto itemStockDto);

    List<ItemStockDo> itemStockDo2DoList(List<ItemStockDto> itemStockDtos);
}
