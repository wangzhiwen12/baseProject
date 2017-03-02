package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.entity.CouponTemplate;

import java.util.List;
import java.util.Map;

public interface CouponTemplateMapper {
	int deleteByPrimaryKey(Integer sid);

	int insert(CouponTemplate record);

	int insertSelective(CouponTemplate record);

	CouponTemplate selectByPrimaryKey(Integer sid);

	int updateByPrimaryKeySelective(CouponTemplate record);

	int updateByPrimaryKey(CouponTemplate record);

	List<CouponTemplate> selectListByParam(CouponTemplate entity);

	List<CouponTemplate> selectPageListByParam(CouponTemplate entity);

	Integer getCountByParam(CouponTemplate entity);

	List<CouponTemplate> selectListByParam(Map<String, Object> paramMap);

	List<CouponTemplate> selectPageListByParam(Map<String, Object> paramMap);

	Integer getCountByParam(Map<String, Object> paramMap);
}