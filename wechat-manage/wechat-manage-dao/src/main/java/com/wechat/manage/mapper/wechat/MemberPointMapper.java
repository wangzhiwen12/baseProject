package com.wechat.manage.mapper.wechat;


import com.wechat.manage.pojo.wechat.entity.MemberPoint;

import java.util.List;
import java.util.Map;

public interface MemberPointMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(MemberPoint record);

    int insertSelective(MemberPoint record);

    MemberPoint selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(MemberPoint record);

    int updateByPrimaryKey(MemberPoint record);

    List<MemberPoint> selectListByParam(Map<String, Object> paramMap);
}