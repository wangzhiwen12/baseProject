package com.wechat.manage.mapper.category;

import com.wechat.manage.pojo.category.entity.TCategoryPropsDict;

public interface TCategoryPropsDictMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(TCategoryPropsDict record);

    int insertSelective(TCategoryPropsDict record);

    TCategoryPropsDict selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(TCategoryPropsDict record);

    int updateByPrimaryKey(TCategoryPropsDict record);
}