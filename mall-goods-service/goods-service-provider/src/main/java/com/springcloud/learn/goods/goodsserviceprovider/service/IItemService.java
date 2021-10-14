package com.springcloud.learn.goods.goodsserviceprovider.service;

import com.springcloud.learn.goods.goodsserviceprovider.domain.ItemStockDo;
import com.springcloud.learn.goods.goodsserviceprovider.mapper.entity.TbItem;

import java.util.List;

public interface IItemService {

    List<TbItem> findItemsByIds(List<Long> ids);

    boolean decreaseStock(List<ItemStockDo> stocks);
}
