package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.entity.MemberPointInfo;
import com.wechat.manage.pojo.wechat.vo.MemberPointInfoReturnDto;

import java.util.List;
import java.util.Map;


public interface MemberPointInfoMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(MemberPointInfo record);

    int insertSelective(MemberPointInfo record);

    MemberPointInfo selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(MemberPointInfo record);

    int updateByPrimaryKey(MemberPointInfo record);

    List<MemberPointInfo> selectListByParam(Map<String, Object> paramMap);

    /**
     * 查询积分明细
     *
     * @param paramMap
     * @return
     */
    List<MemberPointInfoReturnDto> selectMemberPointDetailListByParam(Map<String, Object> paramMap);
}