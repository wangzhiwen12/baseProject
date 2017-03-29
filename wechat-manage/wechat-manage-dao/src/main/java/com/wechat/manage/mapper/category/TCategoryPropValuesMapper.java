package com.wechat.manage.mapper.category;

import com.wechat.manage.pojo.category.entity.TCategoryPropValues;

public interface TCategoryPropValuesMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(TCategoryPropValues record);

    int insertSelective(TCategoryPropValues record);

    TCategoryPropValues selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(TCategoryPropValues record);

    int updateByPrimaryKey(TCategoryPropValues record);
}