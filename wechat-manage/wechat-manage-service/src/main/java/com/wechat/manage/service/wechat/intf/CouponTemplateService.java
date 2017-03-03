package com.wechat.manage.service.wechat.intf;

import com.wechat.manage.pojo.wechat.entity.CouponTemplate;
import com.wechat.manage.vo.DataTableResult;

import java.util.List;
import java.util.Map;

public interface CouponTemplateService {
	public DataTableResult<CouponTemplate> selectPageListByParam(Map<String, Object> paramMap);

	public int insertSelective(CouponTemplate entity);

	public int deleteByPrimaryKey(Integer sid);

	public int updateByPrimaryKeySelective(CouponTemplate entity);

	public List<CouponTemplate> selectListByParam(CouponTemplate entity);
}
