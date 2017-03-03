package com.wechat.manage.service.wechat.intf;


import com.wechat.manage.pojo.wechat.entity.MemberCard;
import com.wechat.manage.pojo.wechat.entity.CouponRule;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by wangxuan on 2016-11-23 0023.
 */
public interface 	MemberCardService {
	public String getCardCode(CouponRule cardRule, String storeCode);

	String generateCardCode(Map<String, Object> paramMap);

	@Transactional
	Map<String, Object> bindMemberCard(Map<String, Object> paraMap) throws Exception;

	@Transactional
	Map<String, Object> cardCancleBind(Map<String, Object> paraMap) throws Exception;

	public MemberCard findMemberCardByParam(Map<String, Object> paramMap);
}
