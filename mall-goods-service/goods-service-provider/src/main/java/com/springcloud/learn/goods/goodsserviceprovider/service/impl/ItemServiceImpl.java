package com.springcloud.learn.goods.goodsserviceprovider.service.impl;

import com.springcloud.learn.example.exception.BizException;
import com.springcloud.learn.goods.goodsserviceprovider.domain.ItemStockDo;
import com.springcloud.learn.goods.goodsserviceprovider.mapper.entity.TbItem;
import com.springcloud.learn.goods.goodsserviceprovider.mapper.entity.TbItemExample;
import com.springcloud.learn.goods.goodsserviceprovider.mapper.persistence.TbItemMapper;
import com.springcloud.learn.goods.goodsserviceprovider.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl  implements IItemService{

    @Autowired
    TbItemMapper tbItemMapper;

    @Override
    public List<TbItem> findItemsByIds(List<Long> ids) {
        TbItemExample tbItemExample = new TbItemExample();
        tbItemExample.createCriteria().andItemIdIn(ids);
        return tbItemMapper.selectByExample(tbItemExample);
    }

    @Override
    public boolean decreaseStock(List<ItemStockDo> stocks) {
        List<Long> ids = stocks.stream().map(ItemStockDo::getItemId).collect(Collectors.toList());
        List<TbItem> tbItems = tbItemMapper.selectStockForUpdate(ids);
        if (tbItems==null || tbItems.isEmpty()){
            throw  new BizException("未找到对应的商品");
        }
        if(ids.size()!=tbItems.size()){
            throw new BizException("部分商品不存在");
        }

         stocks.forEach(itemStockDo -> {
            tbItems.forEach(item-> {
                if (Objects.equals(item.getItemId(), itemStockDo.getItemId())) {
                    if (item.getRestNum() < itemStockDo.getNum()) {
                        throw new BizException(itemStockDo.getItemId() + ":库存不足");
                    }
                }
                TbItem tbItem = new TbItem();
                tbItem.setItemId(itemStockDo.getItemId());
                tbItem.setRestNum(itemStockDo.getNum());
                int row = tbItemMapper.decreaseStock(tbItem);
                if (row < 0) {
                    throw new BizException(itemStockDo.getItemId() + ":库存不足");
                }
                return;
            });
        });
        return true;
    }
}
