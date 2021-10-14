package com.springcloud.learn.goods.goodsserviceprovider.mapper.persistence;

import com.springcloud.learn.goods.goodsserviceprovider.mapper.entity.TbItem;
import com.springcloud.learn.goods.goodsserviceprovider.mapper.entity.TbItemExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TbItemMapper {
    long countByExample(TbItemExample example);

    int deleteByExample(TbItemExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    List<TbItem> selectByExample(TbItemExample example);

    TbItem selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);

    List<TbItem> selectStockForUpdate(List<Long> ids);

    int decreaseStock(@Param("record")TbItem  record);
}