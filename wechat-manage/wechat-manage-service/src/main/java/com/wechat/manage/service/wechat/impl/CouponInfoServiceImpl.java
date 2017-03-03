package com.wechat.manage.service.wechat.impl;

import com.wechat.manage.mapper.wechat.CouponInfoMapper;
import com.wechat.manage.mapper.wechat.CouponTemplateMapper;
import com.wechat.manage.pojo.wechat.entity.CouponInfo;
import com.wechat.manage.pojo.wechat.entity.CouponTemplate;
import com.wechat.manage.pojo.wechat.vo.CouponDto;
import com.wechat.manage.pojo.wechat.vo.CouponForMemDto;
import com.wechat.manage.pojo.wechat.vo.MemberViewDto;
import com.wechat.manage.service.wechat.intf.CouponInfoService;
import com.wechat.manage.utils.Constants;
import com.wechat.manage.vo.DataTableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CouponInfoServiceImpl implements CouponInfoService {

	@Autowired
	CouponInfoMapper couponInfoMapper;
	@Autowired
	CouponTemplateMapper couponTemplateMapper;

	public List<CouponDto> getCouponInfoByOpenIdAndStoreCode(Map<String, Object> paramMap) {
		List<CouponDto> list = couponInfoMapper.getCouponInfoByOpenIdAndStoreCode(paramMap);
		return list;
	}

	/**

	 * 会员详情查询券
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<CouponForMemDto> getCouponInfoForMem(Map<String, Object> paramMap) {
		List<CouponForMemDto> list = couponInfoMapper.getCouponInfoForMem(paramMap);
		return list;
	}

	public List<CouponDto> getCouponInfoByStoreCode(Map<String, Object> paramMap) {
		List<CouponDto> list = couponInfoMapper.getCouponInfoByStoreCode(paramMap);
		return list;
	}

	public List<CouponInfo> getCouponInfoByAndStoreCode(Map<String, Object> paramMap) {
		List<CouponInfo> list = couponInfoMapper.selectPageListByParam(paramMap);
		return list;
	}

	public DataTableResult<CouponInfo> findCouponInfoByPage(Map<String, Object> paramMap) {
		DataTableResult<CouponInfo> page = new DataTableResult<CouponInfo>();
		List<CouponInfo> infoList = couponInfoMapper.selectPageListByParam(paramMap);
		for (CouponInfo couponInfo : infoList) {
			if (couponInfo.getCardType().equals(Constants.COUPON_TYPE_GROUPON)) {
				couponInfo.setCardType(Constants.COUPON_TYPE_GROUPON_NAME);
			} else if (couponInfo.getCardType().equals(Constants.COUPON_TYPE_CASH)) {
				couponInfo.setCardType(Constants.COUPON_TYPE_CASH_NAME);
			} else if (couponInfo.getCardType().equals(Constants.COUPON_TYPE_DISCOUNT)) {
				couponInfo.setCardType(Constants.COUPON_TYPE_DISCOUNT_NAME);
			} else if (couponInfo.getCardType().equals(Constants.COUPON_TYPE_GIFT)) {
				couponInfo.setCardType(Constants.COUPON_TYPE_GIFT_NAME);
			} else if (couponInfo.getCardType().equals(Constants.COUPON_TYPE_GENERAL_COUPON)) {
				couponInfo.setCardType(Constants.COUPON_TYPE_GENERAL_COUPON_NAME);
			}
			// 0.拟稿 1.提交 2.已通过 3未通过
			if (couponInfo.getCouponStatus().equals(Constants.COUPON_STATUS_DRAFT)) {
				couponInfo.setCouponStatus(Constants.COUPON_STATUS_DRAFT_NAME);
			} else if (couponInfo.getCouponStatus().equals(Constants.COUPON_STATUS_NO)) {
				couponInfo.setCouponStatus(Constants.COUPON_STATUS_NO_NAME);
			} else if (couponInfo.getCouponStatus().equals(Constants.COUPON_STATUS_YES)) {
				couponInfo.setCouponStatus(Constants.COUPON_STATUS_YES_NAME);
			} else if (couponInfo.getCouponStatus().equals(Constants.COUPON_STATUS_SUBMIT)) {
				couponInfo.setCouponStatus(Constants.COUPON_STATUS_SUBMIT_NAME);
			}
		}
		page.setAaData(infoList);
		paramMap.put("start", null);
		paramMap.put("limit", null);
		Integer count = couponInfoMapper.getCountByParam(paramMap);
		page.setiTotalRecords(count);
		return page;
	}

	@Transactional
	public int addCouponInfo(CouponInfo entity) {
		CouponTemplate tplEntity = new CouponTemplate();
		tplEntity.setSid(entity.getTplSid());
		List<CouponTemplate> tplList = couponTemplateMapper.selectListByParam(tplEntity);
		if (tplList != null && tplList.size() > 0) {
			entity.setCardType(tplList.get(0).getCouponType());
		}
		return couponInfoMapper.insertSelective(entity);
	}

	public int editCouponInfo(CouponInfo entity) {
		return couponInfoMapper.updateByPrimaryKeySelective(entity);
	}

	public List<MemberViewDto> getMemberViewList(Map<String, Object> paramMap) {
		return couponInfoMapper.getMemberViewList(paramMap);
	}

}
