package com.wechat.manage.mapper.category;

import com.wechat.manage.pojo.category.entity.TProGroup;

import java.util.List;
import java.util.Map;

public interface TProGroupMapper {
    List<String> getProListByGroupId(Map<String, Object> paramMap);

    int deleteByPrimaryKey(Long id);

    int insert(TProGroup record);

    int insertSelective(TProGroup record);

    TProGroup selectByPrimaryKey(Long id);

    /**
     * 查询分组信息
     *
     * @param paramMap
     * @return
     */
    List<TProGroup> selectGroupList(Map<String, Object> paramMap);
    int selectGroupCount(Map<String, Object> paramMap);

    int updateByPrimaryKeySelective(TProGroup record);

    int updateByPrimaryKey(TProGroup record);
}