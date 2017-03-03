package com.wechat.manage.service.wechat.intf;

import com.wechat.manage.pojo.wechat.entity.CouponInfo;
import com.wechat.manage.pojo.wechat.vo.CouponDto;
import com.wechat.manage.pojo.wechat.vo.CouponForMemDto;
import com.wechat.manage.pojo.wechat.vo.MemberViewDto;
import com.wechat.manage.vo.DataTableResult;

import java.util.List;
import java.util.Map;

public interface CouponInfoService {
	public List<CouponInfo> getCouponInfoByAndStoreCode(Map<String, Object> paramMaps);

	public List<CouponDto> getCouponInfoByStoreCode(Map<String, Object> paramMap);

	public List<MemberViewDto> getMemberViewList(Map<String, Object> paramMap);

	public List<CouponDto> getCouponInfoByOpenIdAndStoreCode(Map<String, Object> paramMap);

	public List<CouponForMemDto> getCouponInfoForMem(Map<String, Object> paramMap);

	public DataTableResult<CouponInfo> findCouponInfoByPage(Map<String, Object> paramMap);

	public int addCouponInfo(CouponInfo entity);

	public int editCouponInfo(CouponInfo entity);

}
