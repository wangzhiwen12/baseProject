package com.wechat.manage.mapper.product;

import com.wechat.manage.pojo.product.entity.TProDetail;

public interface TProDetailMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(TProDetail record);

    int insertSelective(TProDetail record);

    TProDetail selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(TProDetail record);

    int updateByPrimaryKey(TProDetail record);
}