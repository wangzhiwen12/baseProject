package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.entity.MsgMass;

import java.util.List;
import java.util.Map;

public interface MsgMassMapper {
	int deleteByPrimaryKey(Long sid);

	int insert(MsgMass record);

	int insertSelective(MsgMass record);

	MsgMass selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(MsgMass record);

	int updateByPrimaryKeyWithBLOBs(MsgMass record);

	int updateByPrimaryKey(MsgMass record);

	List<MsgMass> selectListByParam(Map<String, Object> paramMap);

	List<MsgMass> selectPageListByParam(Map<String, Object> paramMap);

	Integer getCountByParam(Map<String, Object> paramMap);

	List<MsgMass> selectListByParam(MsgMass entity);

	List<MsgMass> selectPageListByParam(MsgMass entity);

	Integer getCountByParam(MsgMass entity);
}