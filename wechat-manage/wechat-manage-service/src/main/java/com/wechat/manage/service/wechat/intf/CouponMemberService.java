package com.wechat.manage.service.wechat.intf;


import com.wechat.manage.pojo.wechat.entity.CouponMember;
import com.wechat.manage.pojo.wechat.vo.CouponInfoDto;
import com.wechat.manage.pojo.wechat.vo.CouponStatisticsDto;
import com.wechat.manage.pojo.wechat.vo.UserCouponInfoDto;
import com.wechat.manage.vo.DataTableResult;

import java.util.Map;

public interface CouponMemberService {
	public String addCouponMember(CouponMember entity);

	DataTableResult<CouponInfoDto> getCouponInfoListByOpenIdAndStoreCdoe(
			Map<String, Object> paramMap);

	public UserCouponInfoDto getCouponCodeByOpenIdAndCardId(Map<String, Object> paramMap);

	public String useCouponMember(CouponMember mem);

	public DataTableResult<CouponStatisticsDto> getCouponStatistics(Map<String, Object> paramMap);

}
