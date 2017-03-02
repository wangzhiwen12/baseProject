package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.vo.CouponDto;
import com.wechat.manage.pojo.wechat.vo.CouponForMemDto;
import com.wechat.manage.pojo.wechat.vo.CouponInfoDto;
import com.wechat.manage.pojo.wechat.vo.MemberViewDto;
import com.wechat.manage.pojo.wechat.entity.CouponInfo;

import java.util.List;
import java.util.Map;

public interface CouponInfoMapper {
	List<CouponDto> getCouponInfoByOpenIdAndStoreCode(Map<String, Object> paramMap);

	List<CouponDto> getCouponInfoByStoreCode(Map<String, Object> paramMap);

	List<MemberViewDto> getMemberViewList(Map<String, Object> paramMap);

	int deleteByPrimaryKey(Long sid);

	int insert(CouponInfo record);

	int insertSelective(CouponInfo record);

	CouponInfo selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(CouponInfo record);

	int updateByPrimaryKeyWithBLOBs(CouponInfo record);

	int updateByPrimaryKey(CouponInfo record);

	List<CouponInfo> selectListByParam(CouponInfo entity);

	List<CouponInfo> selectPageListByParam(CouponInfo entity);

	Integer getCountByParam(CouponInfo entity);

	List<CouponInfo> selectListByParam(Map<String, Object> paramMap);

	List<CouponInfo> selectPageListByParam(Map<String, Object> paramMap);

	Integer getCountByParam(Map<String, Object> paramMap);

	List<CouponInfoDto> getCouponInfoListByOpenIdAndStoreCode(Map<String, Object> paramMap);

	List<CouponForMemDto> getCouponInfoForMem(Map<String, Object> paramMap);
}