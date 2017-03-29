package com.wechat.manage.mapper.category;

import com.wechat.manage.pojo.category.entity.TCategory;

public interface TCategoryMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(TCategory record);

    int insertSelective(TCategory record);

    TCategory selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(TCategory record);

    int updateByPrimaryKey(TCategory record);
}