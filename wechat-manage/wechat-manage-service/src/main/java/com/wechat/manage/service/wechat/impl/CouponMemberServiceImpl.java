package com.wechat.manage.service.wechat.impl;

import com.wechat.manage.pojo.wechat.vo.CouponInfoDto;
import com.wechat.manage.pojo.wechat.vo.CouponStatisticsDto;
import com.wechat.manage.pojo.wechat.entity.CouponInfo;
import com.wechat.manage.pojo.wechat.entity.CouponMember;
import com.wechat.manage.pojo.wechat.entity.CouponTemplate;
import com.wechat.manage.pojo.wechat.entity.DataTableResult;
import com.wechat.manage.pojo.wechat.vo.UserCouponInfoDto;
import com.wechat.manage.mapper.wechat.CouponInfoMapper;
import com.wechat.manage.mapper.wechat.CouponMemberMapper;
import com.wechat.manage.mapper.wechat.CouponRuleMapper;
import com.wechat.manage.mapper.wechat.CouponTemplateMapper;
import com.wechat.manage.service.wechat.intf.CouponMemberService;
import com.wechat.manage.utils.Constants;
import com.wechat.manage.utils.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.*;

@Service
public class CouponMemberServiceImpl implements CouponMemberService {
	@Autowired
	CouponMemberMapper couponMemberMapper;
	@Autowired
	CouponInfoMapper infoMapper;
	@Autowired
	CouponRuleMapper ruleMapper;
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	private CouponTemplateMapper tplMapper;

	private static Logger logger = Logger.getLogger(CouponMemberServiceImpl.class);

	public String addCouponMember(CouponMember entity) {
		// 1.判断是否超过发券数量
		// 2.判断卡券是否在有效期内
		CouponInfo info = new CouponInfo();
		info.setSid(entity.getCouponSid());
		List<CouponInfo> infoList = infoMapper.selectListByParam(info);
		if (infoList != null && infoList.size() > 0) {
			CouponInfo couponInfo = infoList.get(0);
			// 判断卡券是否在有效期内
			Date endTimestamp = couponInfo.getEndTimestamp();
			if (endTimestamp != null) {
				Date date = new Date();
				long time = endTimestamp.getTime() - date.getTime();
				if (time < 0) {
					logger.error("卡券已过期return 0");
					return "卡券已过期";
				}
			}
			Date beginTimestamp = couponInfo.getBeginTimestamp();
			if (beginTimestamp != null) {
				Date date = new Date();
				long time = date.getTime() - beginTimestamp.getTime();
				if (time < 0) {
					logger.error("卡券未开始领取return 0");
					return "卡券未开始领取";
				}
			}
			if (couponInfo.getQuantity() != null) {
				CouponMember couponMember = new CouponMember();
				couponMember.setCouponSid(entity.getCouponSid());
				Integer count = couponMemberMapper.getCountByParam(couponMember);
				if (couponInfo.getQuantity() < count) {
					logger.error("卡券已领取完return 0");
					return "卡券已领取完";
				}
			}
			if (couponInfo.getGetLimit() != null) {
				CouponMember couponMember = new CouponMember();
				couponMember.setCouponSid(entity.getCouponSid());
				couponMember.setMemberSid(entity.getMemberSid());
				Integer count = couponMemberMapper.getCountByParam(couponMember);
				if (couponInfo.getGetLimit() < count) {
					logger.error("卡券已领上限return 0");
					return "卡券已领上限";
				}
			}
			// 生成消费码
			CouponInfo infoDto = infoMapper.selectByPrimaryKey(entity.getCouponSid());
			CouponTemplate tplDto = tplMapper.selectByPrimaryKey(infoDto.getTplSid());
			String couponCode = null;
			// 1.根据前缀查询数据个数
			CouponMember couponMember = new CouponMember();
			couponMember.setCouponStartNo(tplDto.getStartNo());
			Integer count = couponMemberMapper.getCountByParam(couponMember);
			// 2.生成流水号count + 1 补足rule.getNolength()位
			NumberFormat formatter = NumberFormat.getNumberInstance();
			formatter.setMinimumIntegerDigits(Integer.parseInt(tplDto.getNoLength()));
			formatter.setGroupingUsed(false);
			couponCode = formatter.format((count + 1));
			// 3.后缀Math.pow(10, 2)
			double pow = Math.pow(10, Integer.parseInt(tplDto.getSuffixLength()));
			int suffix = (int) (pow * Math.random());
			couponCode = tplDto.getStartNo() + couponCode + suffix;
			entity.setCouponCode(couponCode);
			entity.setCouponStartNo(tplDto.getStartNo());
		} else {
			logger.error("卡券不存在return 0");
			return "卡券不存在";
		}
		couponMemberMapper.insertSelective(entity);
		return "success";
	}

	/**
	 * 根据openID和门店编码获取卡券列表
	 */
	public DataTableResult<CouponInfoDto> getCouponInfoListByOpenIdAndStoreCdoe(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		DataTableResult<CouponInfoDto> page = new DataTableResult<CouponInfoDto>();
		List<CouponInfoDto> infoList = infoMapper.getCouponInfoListByOpenIdAndStoreCode(paramMap);
		for (CouponInfoDto couponInfo : infoList) {
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
		Integer count = couponMemberMapper.getCountByOpenIdAndStoreCode(paramMap);
		page.setiTotalRecords(count);
		return page;
	}

	/**
	 * 查询卡券消费码及其二维码
	 * 
	 * @Methods Name getCouponCodeByOpenIdAndCardId
	 * @Create In 2016年12月16日 By yedong
	 * @param paramMap
	 * @return List<UserCouponInfoDto>
	 */
	public UserCouponInfoDto getCouponCodeByOpenIdAndCardId(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		UserCouponInfoDto mem = couponMemberMapper.getCouponCodeByOpenIdAndCardId(paramMap);
		if (mem.getType() != null && mem.getType().equals("DATE_TYPE_FIX_TERM")) {
			Date date = mem.getCollectionTime();
			int fixedTerm = mem.getFixedTerm();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, fixedTerm);
			Date endDate = calendar.getTime();
			mem.setEndTimestamp(endDate);
		}
		UUID uuid = UUID.randomUUID();
		mem.setCouponQRCode(mem.getCouponCode() + uuid);
		redisUtil.set(mem.getCouponQRCode(), mem.getCouponCode(), 120);

		return mem;
	}

	/**
	 * 卡券核销
	 * 
	 * @Methods Name useCouponMember
	 * @Create In 2016年12月16日 By yedong
	 * @param mem
	 * @return String
	 */
	public String useCouponMember(CouponMember mem) {
		mem.setUseState(0);
		List<CouponMember> cardList = couponMemberMapper.selectListByParam(mem);
		if (cardList != null && cardList.size() > 0) {
			Date useTime = new Date();
			mem.setUseTime(useTime);
			mem.setUseState(1);
			mem.setSid(cardList.get(0).getSid());
			int u = couponMemberMapper.updateByPrimaryKeySelective(mem);
			if (u == 1) {
				return "success";
			} else {
				return "请重试";
			}
		} else {
			return "卡券已使用";
		}
	}

	/**
	 * 核销统计
	 */
	public DataTableResult<CouponStatisticsDto> getCouponStatistics(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		DataTableResult<CouponStatisticsDto> page = new DataTableResult<CouponStatisticsDto>();
		List<CouponStatisticsDto> couponStatistics = couponMemberMapper
				.getCouponStatistics(paramMap);
		for (CouponStatisticsDto dto : couponStatistics) {
			if (dto.getCardType().equals(Constants.COUPON_TYPE_GROUPON)) {
				dto.setCardType(Constants.COUPON_TYPE_GROUPON_NAME);
			} else if (dto.getCardType().equals(Constants.COUPON_TYPE_CASH)) {
				dto.setCardType(Constants.COUPON_TYPE_CASH_NAME);
			} else if (dto.getCardType().equals(Constants.COUPON_TYPE_DISCOUNT)) {
				dto.setCardType(Constants.COUPON_TYPE_DISCOUNT_NAME);
			} else if (dto.getCardType().equals(Constants.COUPON_TYPE_GIFT)) {
				dto.setCardType(Constants.COUPON_TYPE_GIFT_NAME);
			} else if (dto.getCardType().equals(Constants.COUPON_TYPE_GENERAL_COUPON)) {
				dto.setCardType(Constants.COUPON_TYPE_GENERAL_COUPON_NAME);
			}
			if (dto.getSendCount() != 0.0) {
				Double usageRate = dto.getUseCount() / dto.getSendCount();
				dto.setUsageRate(usageRate * 100);
			} else {
				dto.setUsageRate(0.0 * 100);
			}
		}
		page.setAaData(couponStatistics);
		paramMap.put("start", null);
		paramMap.put("limit", null);
		Integer count = couponMemberMapper.getCouponStatisticsCount(paramMap);
		page.setiTotalRecords(count);
		return page;
	}

}
