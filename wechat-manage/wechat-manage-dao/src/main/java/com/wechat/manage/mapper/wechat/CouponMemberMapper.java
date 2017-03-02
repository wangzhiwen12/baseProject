package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.vo.CouponStatisticsDto;
import com.wechat.manage.pojo.wechat.entity.CouponMember;
import com.wechat.manage.pojo.wechat.vo.UserCouponInfoDto;

import java.util.List;
import java.util.Map;

public interface CouponMemberMapper {
	int deleteByPrimaryKey(Long sid);

	int insert(CouponMember record);

	int insertSelective(CouponMember record);

	CouponMember selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(CouponMember record);

	int updateByPrimaryKey(CouponMember record);

	List<CouponMember> selectListByParam(CouponMember entity);

	List<CouponMember> selectPageListByParam(CouponMember entity);

	Integer getCountByParam(CouponMember entity);

	List<CouponMember> selectListByParam(Map<String, Object> paramMap);

	List<CouponMember> selectPageListByParam(Map<String, Object> paramMap);

	Integer getCountByParam(Map<String, Object> paramMap);

	int getCountByOpenIdAndStoreCode(Map<String, Object> paramMap);

	List<CouponStatisticsDto> getCouponStatistics(Map<String, Object> paramMap);

	int getCouponStatisticsCount(Map<String, Object> paramMap);

	UserCouponInfoDto getCouponCodeByOpenIdAndCardId(Map<String, Object> paramMap);
}