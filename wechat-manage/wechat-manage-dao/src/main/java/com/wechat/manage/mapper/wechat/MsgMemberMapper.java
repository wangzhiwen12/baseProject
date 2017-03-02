package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.entity.MsgMember;

public interface MsgMemberMapper {
	int deleteByPrimaryKey(Long sid);

	int insert(MsgMember record);

	int insertSelective(MsgMember record);

	MsgMember selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(MsgMember record);

	int updateByPrimaryKey(MsgMember record);
}