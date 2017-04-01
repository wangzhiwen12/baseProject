package com.wechat.manage.mapper.product;

import com.wechat.manage.pojo.product.entity.TProduct;

public interface TProductMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(TProduct record);

    int insertSelective(TProduct record);

    TProduct selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(TProduct record);

    int updateByPrimaryKey(TProduct record);
}