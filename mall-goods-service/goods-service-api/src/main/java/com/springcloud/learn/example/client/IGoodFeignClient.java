package com.springcloud.learn.example.client;


import com.springcloud.learn.example.api.R;
import com.springcloud.learn.example.dto.ItemStockDto;
import com.springcloud.learn.example.vo.ItemDetailVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@FeignClient(value="goods-service")
public interface IGoodFeignClient {
    /**
     * 根据Id 查询商品详情
     */
    @GetMapping("/items/{ids}")
    R<List<ItemDetailVo>> getItemsByIds(@PathVariable("ids")List<Long> ids);

    /**
     * 锁定库存扣减库存
     */
    @PutMapping(value="/items/stock",consumes = MediaType.APPLICATION_JSON_VALUE)
    R decreaseStock(@RequestBody List<ItemStockDto> stocks);

}
