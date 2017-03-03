package com.wechat.manage.service.wechat.impl;


import com.wechat.manage.mapper.system.AppAccountInfoMapper;
import com.wechat.manage.mapper.wechat.CouponRuleMapper;
import com.wechat.manage.mapper.wechat.MemberCardMapper;
import com.wechat.manage.mapper.wechat.MemberInfoMapper;
import com.wechat.manage.pojo.system.entity.AppAccountInfo;
import com.wechat.manage.pojo.wechat.entity.CouponRule;
import com.wechat.manage.pojo.wechat.entity.MemberCard;
import com.wechat.manage.pojo.wechat.entity.MemberInfo;
import com.wechat.manage.pojo.wechat.vo.MemberInfoReturnDto;
import com.wechat.manage.pojo.wechat.vo.WechatMemberCard;
import com.wechat.manage.service.util.WechatUtil;
import com.wechat.manage.utils.StringUtils;
import com.wechat.manage.service.wechat.intf.MemberCardService;
import com.wechat.manage.service.wechat.intf.MemberInfoService;
import com.wechat.manage.vo.DataTableResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-22 0022.
 */
@Service
public class MemberInfoServiceImpl implements MemberInfoService {

	private static Logger logger = LoggerFactory.getLogger(MemberInfoServiceImpl.class);

	@Autowired
	private CouponRuleMapper ruleMapper;
	@Autowired
	private MemberInfoMapper memberInfoMapper;

	@Autowired
	private MemberCardMapper memberCardMapper;

	@Autowired
	private MemberCardService memberCardService;

	@Autowired
	private AppAccountInfoMapper appMapper;

	@Autowired
	private WechatUtil wechatUtil;

	public DataTableResult<MemberInfo> getMsgMember(Map<String, Object> paramMap) {
		DataTableResult<MemberInfo> page = new DataTableResult<MemberInfo>();
		List<MemberInfo> msgMember = memberInfoMapper.getMsgMember(paramMap);
		page.setAaData(msgMember);
		paramMap.put("start", null);
		paramMap.put("limit", null);
		int count = memberInfoMapper.getMsgMemberCount(paramMap);
		page.setiTotalRecords(count);
		return page;
	}

	public int updateByPrimaryKeySelective(MemberInfo record) {
		return memberInfoMapper.updateByPrimaryKeySelective(record);
	}

	public List<MemberInfo> selectListByParam(MemberInfo record) {
		return memberInfoMapper.selectListByParam(record);
	}

	public MemberInfo findMemberInfoByParam(Map<String, Object> paramMap) {
		MemberInfo dto = null;
		List<MemberInfo> memberInfos = memberInfoMapper.selectListByParam(paramMap);
		if (memberInfos != null && memberInfos.size() == 1) {
			dto = memberInfos.get(0);
		}
		return dto;
	}

	/**
	 * 生成会员编码
	 *
	 * @param paramMap
	 * @return
	 */
	public String generateMemberCode(Map<String, Object> paramMap) {
		logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.generateMemberCode(),para"
				+ paramMap.toString());
		Map<String, Object> objectMap = memberInfoMapper.selectMaxMemberCodeByParam(paramMap);
		Long code = null;
		if (objectMap != null && !objectMap.isEmpty()) {
			Long maxMemberCode = Long.parseLong(objectMap.get("maxMemberCode") + "");
			code = maxMemberCode + 1;
		} else {
			code = 1L;
		}

		logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.generateMemberCode(),return:"
				+ code);
		return code + "";
	}

	public int memberInfoInit(MemberInfo memberInfo) {
		String storeCode = memberInfo.getStoreCode();
		String openid = memberInfo.getOpenid();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("storeCode", storeCode);
		paramMap.put("openid", openid);
		List<MemberInfo> memberInfoList = memberInfoMapper.selectListByParam(paramMap);
		if (memberInfoList != null && memberInfoList.size() > 0) {
			memberInfo.setSid(memberInfoList.get(0).getSid());
			int updateSelective = memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
			if (updateSelective != 1) {
				throw new RuntimeException("会员注册时，数据库插入会员信息（memberinfo）失败！");
			}
		} else {
			int insertSelective = memberInfoMapper.insertSelective(memberInfo);
			if (insertSelective != 1) {
				throw new RuntimeException("会员注册时，数据库插入会员信息（memberinfo）失败！");
			}
		}
		return 0;
	}

	@Transactional
	public Map<String, Object> registerMember_2(MemberInfo memberInfo) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String storeCode = memberInfo.getStoreCode();
		String openid = memberInfo.getOpenid();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("storeCode", storeCode);
		paramMap.put("openid", openid);
		List<MemberInfo> memberInfoList = memberInfoMapper.selectListByParam(paramMap);
		if (memberInfoList != null && memberInfoList.size() > 0) {
			// 判断是否有会员号, 生成临时卡
			if (StringUtils.isEmpty(memberInfoList.get(0).getMemberCode())) {
				// 会员号为空－生成会员号及会员临时卡号
				paramMap.clear();
				String memberCode = generateMemberCode(paramMap);
				memberInfo.setSid(memberInfoList.get(0).getSid());
				memberInfo.setMemberCode(memberCode);
				int u = memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
				if (u != 1) {
					throw new RuntimeException("会员注册时，u失败！");
				}
				// 插入虚拟卡
				MemberCard memberCard = new MemberCard();
				memberCard.setStoreCode(storeCode);
				memberCard.setMemberCode(memberCode);
				paramMap.clear();
				// String generateCardCode =
				// memberCardService.generateCardCode(paramMap);旧
				CouponRule rule = new CouponRule();
				rule.setStoreCode(storeCode);
				List<CouponRule> ruleList = ruleMapper.selectCouponRuleInfo(rule);
				if (ruleList != null && ruleList.size() > 0) {
					// 1.根据前缀查询数据个数
					CouponRule cardRule = ruleList.get(0);
					String generateCardCode = memberCardService.getCardCode(cardRule, storeCode);
					MemberInfo memberInfo2 = new MemberInfo();
					memberInfo2.setSid(memberInfoList.get(0).getSid());
					memberInfo2.setStartNo(cardRule.getStartno());
					int u1 = memberInfoMapper.updateByPrimaryKeySelective(memberInfo2);
					if (u1 != 1) {
						throw new RuntimeException("会员注册时，u失败！");
					}
					logger.info("generateCardCode" + generateCardCode);
					memberCard.setCardCode(generateCardCode);
					memberCard.setCardType(2);
					memberCard.setStatus(0);
					memberCard.setDelFlag(0);
					int i = memberCardMapper.insertSelective(memberCard);
					if (i != 1) {
						throw new RuntimeException("会员注册时，i失败！");
					}
					memberActivation(storeCode, openid);
					returnMap.put("success", "true");
					returnMap.put("desc", "注册成功！");
				} else {
					returnMap.put("success", "false");
					returnMap.put("desc", "用户已注册");
				}
			}
		} else {
			returnMap.put("success", "false");
			returnMap.put("desc", "用户已注册");
		}
		return returnMap;
	}

	/**
	 * 会员激活
	 *
	 * @param storeCode
	 * @param openid
	 *            void
	 * @Methods Name memberActivation
	 * @Create In 2016年12月22日 By yedong
	 */
	public void memberActivation(String storeCode, String openid) {
		Map<String, Object> appMap = new HashMap<String, Object>();
		appMap.put("storecode", storeCode);
		appMap.put("delFlag", "0");
		List<AppAccountInfo> appList = appMapper.selectListByParam(appMap);
		logger.info("--------appList-----------------" + appList);
		if (appList != null && appList.size() > 0) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("storeCode", storeCode);
			paramMap.put("openid", openid);
			List<MemberInfo> infoList = memberInfoMapper.selectListByParam(paramMap);
			paramMap.put("storeCode", storeCode);
			paramMap.put("memberCode", infoList.get(0).getMemberCode());
			List<MemberCard> memberCardList = memberCardMapper.selectListByParam(paramMap);
			WechatMemberCard card = new WechatMemberCard();
			String codeCard = infoList.get(0).getCodeId();
			String[] str = codeCard.split(";");
			card.setCode(str[0]);
			card.setMembership_number(memberCardList.get(0).getCardCode());
			card.setCard_id(str[1]);
			card.setBackground_pic_url(
					"http://mmbiz.qpic.cn/mmbiz_jpg/LViaibuiaT88eeobEO6Rlo0R1z38XjMR2TD5BS5axaoOgFKIHTnS1I3IUaptC5AtOR9UrkaTPx2wcUZ8iaxPehkOibQ/0");
			String memberCardActivate = wechatUtil.memberCardActivate(appList.get(0).getAppid(),
					appList.get(0).getAppsecret(), card);
			logger.info("---------memberCardActivate----------------" + memberCardActivate);
		}
	}

	/**
	 * 注册会员
	 *
	 * @param memberInfo
	 * @return
	 */
	@Transactional
	public Map<String, Object> registerMember(MemberInfo memberInfo) throws Exception {
		logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.registerMember(),para:"
				+ memberInfo.toString());
		String storeCode = memberInfo.getStoreCode();
		String openid = memberInfo.getOpenid();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("storeCode", storeCode);
		paramMap.put("openid", openid);
		List<MemberInfo> memberInfoList = memberInfoMapper.selectListByParam(paramMap);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (memberInfoList.size() == 0) {
			// 插入会员信息
			paramMap.clear();
			String generateMemberCode = generateMemberCode(paramMap);
			memberInfo.setMemberCode(generateMemberCode);
			int insertSelective = memberInfoMapper.insertSelective(memberInfo);
			if (insertSelective != 1) {
				throw new RuntimeException("会员注册时，数据库插入会员信息（memberinfo）失败！");
			}

			// 插入虚拟卡
			MemberCard memberCard = new MemberCard();
			memberCard.setStoreCode(storeCode);
			memberCard.setMemberCode(generateMemberCode);
			paramMap.clear();
			String generateCardCode = memberCardService.generateCardCode(paramMap);
			memberCard.setCardCode(generateCardCode);
			memberCard.setCardType(2);
			memberCard.setStatus(0);
			memberCard.setDelFlag(0);
			memberCardMapper.insertSelective(memberCard);
		} else if (memberInfoList.size() == 1) {
			MemberInfo memberInfo1 = memberInfoList.get(0);
			if (StringUtils.isEmpty(memberInfo1.getMemberCode())) {
				logger.info("memberInfo1.getMemberCode()---" + memberInfo1.getMemberCode());
				logger.info("falg---------" + StringUtils.isEmpty(memberInfo1.getMemberCode()));
				String generateMemberCode = generateMemberCode(paramMap);
				MemberInfo memberInfo_1 = new MemberInfo();
				memberInfo_1.setSid(memberInfo1.getSid());
				memberInfo_1.setMemberCode(generateMemberCode);
				int insertSelective = memberInfoMapper.updateByParaSelective(memberInfo_1);
				logger.info("insertSelective---------" + insertSelective);
				if (insertSelective == 1) {
					memberInfo1.setMemberCode(generateMemberCode);
				}
			}

			paramMap.clear();
			paramMap.put("storeCode", storeCode);
			paramMap.put("memberCode", memberInfo1.getMemberCode());
			paramMap.put("cardType", 2);
			List<MemberCard> memberCardList = memberCardMapper.selectListByParam(paramMap);
			if (memberCardList.size() == 0) {
				// 插入虚拟卡
				MemberCard memberCard = new MemberCard();
				memberCard.setStoreCode(storeCode);
				memberCard.setMemberCode(memberInfo1.getMemberCode());
				paramMap.clear();
				String generateCardCode = memberCardService.generateCardCode(paramMap);
				memberCard.setCardCode(generateCardCode);
				memberCard.setCardType(2);
				memberCard.setStatus(0);
				memberCard.setDelFlag(0);
				memberCardMapper.insertSelective(memberCard);
			} else if (memberCardList.size() > 0) {
				MemberCard memberCard = memberCardList.get(0);
				memberCard.setStatus(0);
				memberCard.setDelFlag(0);
				memberCardMapper.updateByParaSelective(memberCard);
			}
		} else {
			throw new RuntimeException("会员注册时，会员信息（memberinfo）存在重复信息！");
		}

		returnMap.put("success", "true");
		returnMap.put("desc", "注册成功！");

		Map<String, Object> appMap = new HashMap<String, Object>();
		appMap.put("storecode", storeCode);
		appMap.put("delFlag", "0");
		List<AppAccountInfo> appList = appMapper.selectListByParam(appMap);
		logger.info("--------appList-----------------" + appList);
		if (appList != null && appList.size() > 0) {
			paramMap.put("storeCode", storeCode);
			paramMap.put("openid", openid);
			List<MemberInfo> infoList = memberInfoMapper.selectListByParam(paramMap);
			paramMap.put("storeCode", storeCode);
			paramMap.put("memberCode", infoList.get(0).getMemberCode());
			paramMap.put("cardType", 2);
			List<MemberCard> memberCardList = memberCardMapper.selectListByParam(paramMap);
			WechatMemberCard card = new WechatMemberCard();
			card.setCode(infoList.get(0).getCodeId());
			card.setMembership_number(memberCardList.get(0).getCardCode());
			card.setCard_id(appList.get(0).getCardId());
			card.setBackground_pic_url(
					"http://mmbiz.qpic.cn/mmbiz_jpg/LViaibuiaT88eeobEO6Rlo0R1z38XjMR2TD5BS5axaoOgFKIHTnS1I3IUaptC5AtOR9UrkaTPx2wcUZ8iaxPehkOibQ/0");
			String memberCardActivate = wechatUtil.memberCardActivate(appList.get(0).getAppid(),
					appList.get(0).getAppsecret(), card);
			logger.info("---------memberCardActivate----------------" + memberCardActivate);
		}

		logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.registerMember(),return:"
				+ returnMap.toString());
		return returnMap;
	}

	/**
	 * 查询会员及会员卡信息
	 *
	 * @param
	 * @return
	 */
	public Map<String, Object> getMemberInfo(Map<String, Object> paraMap) {
		logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.getMemberInfo(),para:"
				+ paraMap.toString());
		String appid = paraMap.get("appid") + "";
		String openid = paraMap.get("openid") + "";
		String storeCode = paraMap.get("storeCode") + "";

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("storeCode", storeCode);
		paramMap.put("openid", openid);
		List<MemberInfoReturnDto> memberInfoReturnDtoList = memberInfoMapper
				.selectMemberAndCardInfoListByParam(paramMap);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (memberInfoReturnDtoList.size() == 0) {
			returnMap.put("code", "0");
			returnMap.put("desc", "未注册会员信息！");
		} else if (memberInfoReturnDtoList.size() == 1) {
			returnMap.put("code", "1");
			returnMap.put("desc", "查询到会员及会员卡信息！");
			returnMap.put("obj", memberInfoReturnDtoList.get(0));
		} else {
			throw new RuntimeException(
					"查询会员及会员卡信息（com.wfj.service.impl.MemberInfoServiceImpl.getMemberInfo）：一个门店下一个会员有两张及以上会员！");
		}

		logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.getMemberInfo(),return:"
				+ returnMap.toString());
		return returnMap;
	}

	/**
	 * 修改支付密码
	 *
	 * @param
	 * @return
	 */
	@Transactional
	public Map<String, Object> changePayPassword(Map<String, Object> paraMap) throws Exception {
		logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.changePayPassword(),para:"
				+ paraMap.toString());
		String openid = paraMap.get("openid") + "";
		String storeCode = paraMap.get("storeCode") + "";
		String oldPayPwd = paraMap.get("oldPayPwd") + "";
		String newPayPwd = paraMap.get("newPayPwd") + "";

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openid", openid);
		paramMap.put("storeCode", storeCode);
		List<MemberInfo> memberInfoList = memberInfoMapper.selectListByParam(paramMap);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (memberInfoList.size() == 1) {
			MemberInfo memberInfo = memberInfoList.get(0);
			String password = memberInfo.getPassword();
			if (oldPayPwd.equals(password)) {
				memberInfo.setPassword(newPayPwd);// 修改密码
				memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
				returnMap.put("success", "true");
				returnMap.put("desc", "修改密码成功！");
			} else {
				returnMap.put("success", "false");
				returnMap.put("desc", "旧密码不正确！");
			}
		} else if (memberInfoList.size() == 0) {
			returnMap.put("success", "false");
			returnMap.put("desc", "未注册会员信息！");
		} else {
			throw new RuntimeException(
					"修改支付密码（com.wfj.service.impl.MemberInfoServiceImpl.changePayPassword）时出现重复会员！");
		}

		logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.changePayPassword(),return:"
				+ returnMap.toString());
		return returnMap;
	}

	/**
	 * 查询个人资料(会员信息以及门店信息)
	 *
	 * @param paraMap
	 * @return
	 */
	public MemberInfoReturnDto findMemberAndStoreInfoByPara(Map<String, Object> paraMap) {
		logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.findMemberInfoByPara(),para:"
				+ paraMap.toString());
		String openid = paraMap.get("openid") + "";
		String storeCode = paraMap.get("storeCode") + "";

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openid", openid);
		paramMap.put("storeCode", storeCode);
		List<MemberInfoReturnDto> returnDtoList = memberInfoMapper
				.findMemberAndStoreInfoByPara(paramMap);
		MemberInfoReturnDto returnDto = new MemberInfoReturnDto();
		if (returnDtoList.size() > 0) {
			returnDto = returnDtoList.get(0);
		}
		logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.findMemberInfoByPara(),return:"
				+ returnDto.toString());
		return returnDto;
	}

	/**
	 * 获取当前会员信息
	 *
	 * @param paraMap
	 * @return
	 */
	public MemberInfoReturnDto queryCurMemberInfo(Map<String, Object> paraMap) {
		logger.info("start queryCurMemberInfo,para:" + paraMap.toString());

		List<MemberInfoReturnDto> returnDtoList = memberInfoMapper.selectCurMemberInfo(paraMap);
		MemberInfoReturnDto returnDto = new MemberInfoReturnDto();
		if (returnDtoList.size() > 0) {
			returnDto = returnDtoList.get(0);
		}
		logger.info("end queryCurMemberInfo,return:" + returnDto.toString());
		return returnDto;
	}

	/**
	 * 修改会员信息(修改个人资料)
	 *
	 * @param memberInfo
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Map<String, Object> modifyMemberInfo(MemberInfo memberInfo) throws Exception {
		logger.info("start com.wfj.service.impl.MemberInfoServiceImpl.modifyMemberInfo(),para:"
				+ memberInfo.toString());
		String openid = memberInfo.getOpenid();
		String storeCode = memberInfo.getStoreCode();
		String memberCode = memberInfo.getMemberCode();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("openid", openid);
		paramMap.put("storeCode", storeCode);
		List<MemberInfo> memberInfoList = memberInfoMapper.selectListByParam(paramMap);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (memberInfoList.size() == 1) {
			int i = memberInfoMapper.updateByParaSelective(memberInfo);
			if (i == 1) {
				returnMap.put("success", "true");
				returnMap.put("desc", "修改个人资料成功！");
			} else {
				throw new RuntimeException(
						"修改个人资料（com.wfj.service.impl.MemberInfoServiceImpl.changePayPassword）时操作数据库失败！");
			}
		} else if (memberInfoList.size() == 0) {
			returnMap.put("success", "false");
			returnMap.put("desc", "未注册会员信息！");
		} else {
			throw new RuntimeException(
					"修改会员信息（com.wfj.service.impl.MemberInfoServiceImpl.modifyMemberInfo）时出现重复会员！");
		}

		logger.info("end com.wfj.service.impl.MemberInfoServiceImpl.modifyMemberInfo(),return:"
				+ returnMap.toString());
		return returnMap;
	}

}
