package com.wechat.manage.mapper.category;

import com.wechat.manage.pojo.category.entity.TCategoryValuesDict;

public interface TCategoryValuesDictMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(TCategoryValuesDict record);

    int insertSelective(TCategoryValuesDict record);

    TCategoryValuesDict selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(TCategoryValuesDict record);

    int updateByPrimaryKey(TCategoryValuesDict record);
}