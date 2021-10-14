package com.springcloud.learn.goods.goodsserviceprovider.feign;

import com.springcloud.learn.example.api.R;
import com.springcloud.learn.example.client.IGoodFeignClient;
import com.springcloud.learn.example.dto.ItemStockDto;
import com.springcloud.learn.example.vo.ItemDetailVo;
import com.springcloud.learn.goods.goodsserviceprovider.converter.ItemConverter;
import com.springcloud.learn.goods.goodsserviceprovider.domain.ItemStockDo;
import com.springcloud.learn.goods.goodsserviceprovider.mapper.entity.TbItem;
import com.springcloud.learn.goods.goodsserviceprovider.service.IItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class GoodsFeignClient implements IGoodFeignClient {

    @Autowired
    IItemService itemService;

    @Autowired
    ItemConverter itemConverter;

    @Override
    public R<List<ItemDetailVo>> getItemsByIds(List<Long> ids) {
        log.info("begin:: begin GoodsFeignClient.getItemsByIds " + ids);
        List<TbItem> tbItems = itemService.findItemsByIds(ids);
        List<ItemDetailVo> itemDetailVos = itemConverter.itemDetail2VoList(tbItems);
        return new R.Builder<List<ItemDetailVo>>().setData(itemDetailVos).buildOk();
    }

    @Override
    public R decreaseStock(List<ItemStockDto> stocks) {
        log.info("begin:: begin GoodsFeignClient.decreaseStock " + stocks);
        List<ItemStockDo> itemStockDos = itemConverter.itemStockDo2DoList(stocks);
        boolean rs = itemService.decreaseStock(itemStockDos);
        return new R.Builder<>().buildOk();
    }
}
