package com.wechat.manage.mapper.category;

import com.wechat.manage.pojo.category.entity.TProGroupRelation;

public interface TProGroupRelationMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(TProGroupRelation record);

    int insertSelective(TProGroupRelation record);

    TProGroupRelation selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(TProGroupRelation record);

    int updateByPrimaryKey(TProGroupRelation record);
}