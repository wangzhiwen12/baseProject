package com.wechat.manage.mapper.wechat;


import com.wechat.manage.pojo.wechat.entity.Material;

import java.util.List;
import java.util.Map;

public interface MaterialMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);

    List<Material> selectListByParam(Material entity);

    List<Material> selectPageListByParam(Material entity);

    Integer getCountByParam(Material entity);

    List<Material> selectListByParam(Map<String, Object> paramMap);

    List<Material> selectPageListByParam(Map<String, Object> paramMap);

    Integer getCountByParam(Map<String, Object> paramMap);
}