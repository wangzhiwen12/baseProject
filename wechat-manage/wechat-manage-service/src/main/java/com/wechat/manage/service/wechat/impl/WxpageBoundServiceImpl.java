package com.wechat.manage.service.wechat.impl;

import com.wechat.manage.pojo.wechat.vo.BoundCarePara;
import com.wechat.manage.pojo.wechat.vo.MemberInfoReturnDto;
import com.wechat.manage.pojo.wechat.entity.MemberCard;
import com.wechat.manage.pojo.wechat.entity.MemberInfo;
import com.wechat.manage.pojo.wechat.entity.WxpageBound;
import com.wechat.manage.mapper.wechat.MemberCardMapper;
import com.wechat.manage.mapper.wechat.MemberInfoMapper;
import com.wechat.manage.mapper.wechat.WxpageBoundMapper;
import com.wechat.manage.service.wechat.intf.IWxpageBoundService;
import com.wechat.manage.service.wechat.intf.MemberInfoService;
import com.wechat.manage.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxpageBoundServiceImpl implements IWxpageBoundService {

	@Autowired
	WxpageBoundMapper boundMapper;
	@Autowired
	MemberInfoMapper memberInfoMapper;
	@Autowired
	MemberCardMapper cardMapper;
	@Autowired
	MemberInfoServiceImpl memberInfoService;
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	MemberInfoService memberService;

	public String insertSelective(WxpageBound entity) {
		int i = boundMapper.insertSelective(entity);
		if (i == 1) {
			return "success";
		} else {
			return "error";
		}
	}

	public String updateByPrimaryKeySelective(WxpageBound entity) {
		int i = boundMapper.updateByPrimaryKeySelective(entity);
		if (i == 1) {
			return "success";
		} else {
			return "error";
		}
	}

	public WxpageBound getInfoByStroeCode(Map<String, Object> paramMap) {
		WxpageBound wxpageBound = boundMapper.getInfoByStroeCodeAndType(paramMap);
		if (wxpageBound != null) {
			return wxpageBound;
		}
		return null;
	}

	public String boundCard(BoundCarePara para) {
		String reString = "";
		if (para.getYzm() != null && para.getYzm() != "") {
			// 与redis中的数据对比
			// String yzm = redisUtil.get(para.getSjh(), "0000");
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("storeCode", para.getStoreCode());
		paramMap.put("openid", para.getOpenId());
		List<MemberInfoReturnDto> returnDto = memberInfoMapper
				.selectMemberAndCardInfoListByParam(paramMap);
		// 根据openId与storeCode判断是否有卡，1：存在实体卡，需提前解绑；2：存在电子卡，先解绑电子卡再绑定实体卡；3,：无卡直接绑定
		if (returnDto != null && returnDto.size() > 0) {
			if (returnDto.get(0).getCardType() == 1) {
				reString = "已绑定实体卡，如需更换，请先解绑";
			} else {
				// 解绑电子卡
				para.setCardSid(returnDto.get(0).getSid());
				memberInfoMapper.deleteByPrimaryKey(returnDto.get(0).getSid());
				reString = baundMethod(para);
			}
		} else {
			reString = baundMethod(para);
		}
		return reString;
	}

	private String baundMethod(BoundCarePara para) {
		if (para.getSjh() != null && para.getSjh() != "") {
			// 手机号，生日，密码，会员卡号，验证码--会员卡号，身份证号
			// 按照手机号绑定
			MemberInfo member = new MemberInfo();
			member.setStoreCode(para.getStoreCode());
			member.setOpenid(para.getOpenId());
			List<MemberInfo> infoList = memberInfoMapper.selectListByParam(member);
			member.setMobile(para.getSjh());
			String memberCode = "";
			if (infoList.get(0).getMemberCode() != null && infoList.get(0).getMemberCode() != "") {
				memberCode = infoList.get(0).getMemberCode();
			} else {
				// 用户未注册，生成memberCode
				Map<String, Object> paramMap = new HashMap<String, Object>();
				memberCode = memberInfoService.generateMemberCode(paramMap);
			}
			member.setMemberCode(memberCode);
			if (para.getPwd() != null && para.getPwd() != "") {
				member.setPassword(para.getPwd());
			} else {
				member.setPassword(para.getSjh().substring(5, 11));
			}
			if (para.getSr() != null && para.getSr() != "") {
				member.setBrithday(para.getSr());
			}
			member.setSid(infoList.get(0).getSid());
			memberInfoMapper.updateByPrimaryKeySelective(member);

			MemberCard card = new MemberCard();
			card.setMemberCode(memberCode);
			card.setCardType(1);
			card.setStoreCode(para.getStoreCode());
			// 获取卡号，暂时默认
			if (para.getHykh() != null && para.getHykh() != "") {
				card.setCardCode(para.getHykh());
			} else {
				card.setCardCode(para.getStoreCode() + "00000000" + memberCode);
			}
			if (para.getCardSid() != null) {
				card.setSid(para.getCardSid());
				cardMapper.updateByPrimaryKeySelective(card);
			} else {
				cardMapper.insertSelective(card);
			}
		} else {
			// 按照实体卡绑定
			MemberInfo member = new MemberInfo();
			member.setStoreCode(para.getStoreCode());
			member.setOpenid(para.getOpenId());
			List<MemberInfo> infoList = memberInfoMapper.selectListByParam(member);
			member.setSid(infoList.get(0).getSid());
			String memberCode = "";
			if (infoList.get(0).getMemberCode() != null && infoList.get(0).getMemberCode() != "") {
				memberCode = infoList.get(0).getMemberCode();
			} else {
				// 用户未注册，生成memberCode
				Map<String, Object> paramMap = new HashMap<String, Object>();
				memberCode = memberInfoService.generateMemberCode(paramMap);
			}
			member.setMemberCode(memberCode);
			// 根据会员卡号获取手机号-暂时默认13512349876
			MemberCard card = new MemberCard();
			card.setMemberCode(memberCode);
			card.setCardType(1);
			card.setStoreCode(para.getStoreCode());
			// 获取卡号，暂时默认
			card.setCardCode(para.getHykhTwo());
			if (para.getCardSid() != null) {
				card.setSid(para.getCardSid());
				cardMapper.updateByPrimaryKeySelective(card);
			} else {
				cardMapper.insertSelective(card);
			}
			member.setPassword("349876");
			member.setMobile("13512349876");
			memberInfoMapper.updateByPrimaryKeySelective(member);
		}
		// 激活会员卡
		memberService.memberActivation(para.getStoreCode(), para.getOpenId());
		return "绑定成功";
	}
}
