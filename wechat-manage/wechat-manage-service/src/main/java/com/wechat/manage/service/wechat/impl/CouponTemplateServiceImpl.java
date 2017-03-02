package com.wechat.manage.service.wechat.impl;

import com.wechat.manage.pojo.wechat.entity.CouponInfo;
import com.wechat.manage.pojo.wechat.entity.CouponTemplate;
import com.wechat.manage.pojo.wechat.entity.DataTableResult;
import com.wechat.manage.mapper.wechat.CouponInfoMapper;
import com.wechat.manage.mapper.wechat.CouponTemplateMapper;
import com.wechat.manage.service.wechat.intf.CouponTemplateService;
import com.wechat.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CouponTemplateServiceImpl implements CouponTemplateService {

	@Autowired
	private CouponTemplateMapper couponTemplateMapper;
	@Autowired
	private CouponInfoMapper couponInfoMapper;

	@Transactional
	public int insertSelective(CouponTemplate entity) {
		return couponTemplateMapper.insertSelective(entity);
	}

	@Transactional
	public int deleteByPrimaryKey(Integer sid) {
		CouponInfo couponInfo = new CouponInfo();
		couponInfo.setTplSid(sid);
		int count = couponInfoMapper.getCountByParam(couponInfo);
		if (count == 0) {
			CouponTemplate entity = new CouponTemplate();
			entity.setSid(sid);
			entity.setIfdel(1);
			return couponTemplateMapper.updateByPrimaryKeySelective(entity);
		} else {
			return 0;
		}
	}

	/**
	 * update
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2016年12月9日 By yedong
	 * @param entity
	 * @return int
	 */
	@Transactional
	public int updateByPrimaryKeySelective(CouponTemplate entity) {
		return couponTemplateMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 
	 * @Methods Name selectPageListByParam
	 * @Create In 2016年12月9日 By yedong
	 * @param paramMap
	 * @return DataTableResult<CouponTemplate>
	 */
	public DataTableResult<CouponTemplate> selectPageListByParam(Map<String, Object> paramMap) {
		DataTableResult<CouponTemplate> page = new DataTableResult<CouponTemplate>();

		List<CouponTemplate> couponList = couponTemplateMapper.selectPageListByParam(paramMap);
		if (couponList != null && couponList.size() > 0) {
			for (CouponTemplate couponTPL : couponList) {
				if (couponTPL.getCouponType().equals(Constants.COUPON_TYPE_GROUPON)) {
					couponTPL.setCouponType(Constants.COUPON_TYPE_GROUPON_NAME);
				} else if (couponTPL.getCouponType().equals(Constants.COUPON_TYPE_CASH)) {
					couponTPL.setCouponType(Constants.COUPON_TYPE_CASH_NAME);
				} else if (couponTPL.getCouponType().equals(Constants.COUPON_TYPE_DISCOUNT)) {
					couponTPL.setCouponType(Constants.COUPON_TYPE_DISCOUNT_NAME);
				} else if (couponTPL.getCouponType().equals(Constants.COUPON_TYPE_GIFT)) {
					couponTPL.setCouponType(Constants.COUPON_TYPE_GIFT_NAME);
				} else if (couponTPL.getCouponType().equals(Constants.COUPON_TYPE_GENERAL_COUPON)) {
					couponTPL.setCouponType(Constants.COUPON_TYPE_GENERAL_COUPON_NAME);
				}
			}
			page.setAaData(couponList);
		}
		paramMap.put("start", null);
		paramMap.put("limit", null);
		Integer count = couponTemplateMapper.getCountByParam(paramMap);
		page.setiTotalRecords(count);
		return page;
	}

	public List<CouponTemplate> selectListByParam(CouponTemplate entity) {
		return couponTemplateMapper.selectListByParam(entity);
	}

	public int getCountByParam(CouponTemplate entity) {
		return couponTemplateMapper.getCountByParam(entity);
	}

}
