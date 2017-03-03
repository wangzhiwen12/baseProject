package com.wechat.manage.service.wechat.impl;


import com.wechat.manage.mapper.wechat.MemberCardMapper;
import com.wechat.manage.mapper.wechat.MemberInfoMapper;
import com.wechat.manage.pojo.wechat.entity.CouponRule;
import com.wechat.manage.pojo.wechat.entity.MemberCard;
import com.wechat.manage.pojo.wechat.vo.MemberInfo;
import com.wechat.manage.pojo.wechat.vo.MemberInfoReturnDto;
import com.wechat.manage.service.util.WechatUtil;
import com.wechat.manage.service.wechat.intf.MemberCardService;
import com.wechat.manage.service.wechat.intf.MemberInfoService;
import com.wechat.manage.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-23 0023.
 */
@Service
public class MemberCardServiceImpl implements MemberCardService {

    private static Logger logger = LoggerFactory.getLogger(MemberCardService.class);

    @Autowired
    private MemberCardMapper memberCardMapper;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private WechatUtil wechatUtil;
    @Autowired
    private MemberInfoMapper memberMapper;

    public String getCardCode(CouponRule cardRule, String storeCode) {
        // 1.获取该门店电子卡生成规则；2.已达到最大数；3.4.
        String cardCode = "";
        // 1.根据前缀查询数据个数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startNo", cardRule.getStartno());
        int count = memberMapper.getCountByParam(paramMap);
        // 2.生成流水号count + 1 补足rule.getNolength()位
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(cardRule.getNolength());
        formatter.setGroupingUsed(false);
        cardCode = formatter.format((count + 1));
        // 3.后缀Math.pow(10, 2)
        double pow = Math.pow(10, Integer.parseInt(cardRule.getSuffixlength()));
        int suffix = (int) (pow * Math.random());
        String str = String.format("%0" + cardRule.getSuffixlength() + "d", suffix);
        System.out.println(str); // 0001
        cardCode = cardRule.getStartno() + cardCode + suffix;
        return cardCode;
    }

    /**
     * 生成虚拟卡编码
     *
     * @param paramMap
     * @return
     */
    public String generateCardCode(Map<String, Object> paramMap) {
        logger.info("start com.wfj.service.impl.MemberCardServiceImpl.generateCardCode(),para"
                + paramMap.toString());
        Map<String, Object> objectMap = memberCardMapper.selectMaxCardCodeByParam(paramMap);
        Long code = null;
        if (objectMap != null && !objectMap.isEmpty()) {
            Long maxCardCode = Long.parseLong(objectMap.get("maxCardCode") + "");
            code = maxCardCode + 1;
        } else {
            code = 1L;
        }

        logger.info(
                "end com.wfj.service.impl.MemberCardServiceImpl.generateCardCode(),return:" + code);
        return code + "";
    }

    /**
     * 绑定卡
     *
     * @param paraMap
     * @return
     */
    @Transactional
    public Map<String, Object> bindMemberCard(Map<String, Object> paraMap) throws Exception {
        logger.info("start com.wfj.service.impl.MemberCardServiceImpl.bindMemberCard(),para:"
                + paraMap.toString());
        String storeCode = paraMap.get("storeCode") + "";
        String openid = paraMap.get("openid") + "";
        String cardType = paraMap.get("cardType") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeCode", storeCode);
        paramMap.put("openid", openid);

        List<com.wechat.manage.pojo.wechat.entity.MemberInfo> memberInfoList = memberInfoMapper.selectListByParam(paramMap);
        com.wechat.manage.pojo.wechat.entity.MemberInfo tempMemberInfo = null;

        // 未生成会员信息（memberinfo）绑定卡操作，先生成会员信息（memberinfo）
        if (memberInfoList.size() == 0) {
            // paramMap.clear();
            // paramMap.put("storeCode", storeCode);
            // List<AppAccountInfo> appAccountInfoList =
            // appAccountInfoService.queryAppAccount(paramMap);
            // if (appAccountInfoList.size() == 1) {
            // AppAccountInfo appAccountInfo = appAccountInfoList.get(0);
            MemberInfo openid_userinfo = wechatUtil.Openid_userinfo(openid,
                    paraMap.get("appid") + "", paraMap.get("secret") + "");
            com.wechat.manage.pojo.wechat.entity.MemberInfo memberInfo = new com.wechat.manage.pojo.wechat.entity.MemberInfo();
            paramMap.clear();
            String generateMemberCode = memberInfoService.generateMemberCode(paramMap);
            memberInfo.setMemberCode(generateMemberCode);
            memberInfo.setSubscribe(openid_userinfo.getSubscribe());
            memberInfo.setNickname(openid_userinfo.getNickname());
            memberInfo.setSex(openid_userinfo.getSex());
            memberInfo.setCity(openid_userinfo.getCity());
            memberInfo.setCountry(openid_userinfo.getCountry());
            memberInfo.setProvince(openid_userinfo.getProvince());
            memberInfo.setLanguage(openid_userinfo.getLanguage());
            memberInfo.setHeadimgurl(openid_userinfo.getHeadimgurl());
            memberInfo.setSubscribeTime(openid_userinfo.getSubscribe_time());
            memberInfo.setRemark(openid_userinfo.getRemark());
            memberInfo.setGroupid(openid_userinfo.getGroupid());
            memberInfo.setUnionid(openid_userinfo.getUnionid());
            memberInfo.setOpenid(openid);
            memberInfoService.registerMember(memberInfo);// 注册会员

            tempMemberInfo = memberInfo;
            // } else {
            // throw new
            // RuntimeException("com.wfj.service.impl.MemberCardServiceImpl.bindMemberCard：绑定卡操作时，查询门店appid等信息错误！");
            // }
        } else if (memberInfoList.size() == 1) {// 已注册绑定
            tempMemberInfo = memberInfoList.get(0);
        } else {// 多个会员信息
            throw new RuntimeException(
                    "com.wfj.service.impl.MemberCardServiceImpl.bindMemberCard：绑定卡操作时，同一storeCode、openid有两个会员账号！");
        }

        Map<String, Object> returnMap = new HashMap<String, Object>();
        String cardLevel = paraMap.get("cardLevel") + "";
        String memberCode = tempMemberInfo.getMemberCode();

        // 判断是否绑定了会员卡
        paramMap.clear();
        paramMap.put("storeCode", storeCode);
        paramMap.put("memberCode", memberCode);
        List<MemberCard> bindCardList = memberCardMapper.selectListByParam(paramMap);

        if ("1".equals(cardType)) {// 实体卡绑定
            // 将已经绑定的卡废弃
            if (bindCardList.size() > 0) {
                for (MemberCard memberCard : bindCardList) {
                    memberCard.setStatus(1);
                    memberCard.setDelFlag(0);
                    memberCardMapper.updateByParaSelective(memberCard);
                }
            }

            // 判断此卡是否数据是否存在
            String cardCode = paraMap.get("cardCode") + "";
            paramMap.clear();
            paramMap.put("storeCode", storeCode);
            paramMap.put("memberCode", memberCode);
            paramMap.put("cardCode", cardCode);
            paramMap.put("cardType", 1);// 实体卡
            List<MemberCard> memberCardList = memberCardMapper.selectListByParam(paramMap);
            if (memberCardList.size() == 0) {
                MemberCard memberCard = new MemberCard();
                memberCard.setStoreCode(storeCode);
                memberCard.setMemberCode(memberCode);
                memberCard.setCardCode(cardCode);
                memberCard.setCardType(1);
                memberCard.setStatus(0);
                memberCard.setDelFlag(0);
                if (StringUtils.isNotEmpty(cardLevel)) {
                    memberCard.setCardLevel(Integer.parseInt(cardLevel));
                }
                memberCardMapper.insertSelective(memberCard);
                returnMap.put("success", "true");
                returnMap.put("desc", "绑定成功！");
            } else if (memberCardList.size() == 1 && memberCardList.get(0).getStatus() == 1) {
                MemberCard tempMemberCard = memberCardList.get(0);
                tempMemberCard.setStatus(0);
                tempMemberCard.setDelFlag(0);
                memberCardMapper.updateByParaSelective(tempMemberCard);
                returnMap.put("success", "true");
                returnMap.put("desc", "绑定成功！");
            } else if (memberCardList.size() == 1 && memberCardList.get(0).getStatus() == 0) {
                returnMap.put("success", "false");
                returnMap.put("desc", "该卡已经绑定了！");
            } else {
                throw new RuntimeException(
                        "com.wfj.service.impl.MemberCardServiceImpl.bindMemberCard：绑定卡操作，同一门店、会员号、卡号重复数据！");
            }
        } else if ("2".equals(cardType)) {// 虚拟卡
            paramMap.clear();
            paramMap.put("storeCode", storeCode);
            paramMap.put("memberCode", memberCode);
            paramMap.put("cardType", 2);
            List<MemberCard> virtualCardList = memberCardMapper.selectListByParam(paramMap);
            if (virtualCardList.size() == 0) {
                MemberCard memberCard = new MemberCard();
                memberCard.setStoreCode(storeCode);
                memberCard.setMemberCode(memberCode);
                paramMap.clear();
                String generateCardCode = generateCardCode(paramMap);
                memberCard.setCardCode(generateCardCode);
                memberCard.setCardType(2);
                memberCard.setStatus(0);
                memberCard.setDelFlag(0);
                if (StringUtils.isNotEmpty(cardLevel)) {
                    memberCard.setCardLevel(Integer.parseInt(cardLevel));
                }
                memberCardMapper.insertSelective(memberCard);
            } else if (virtualCardList.size() > 0) {
                MemberCard memberCard = virtualCardList.get(0);
                memberCard.setDelFlag(0);
                memberCard.setStatus(0);
                memberCardMapper.updateByParaSelective(memberCard);
            }
            returnMap.put("success", "true");
            returnMap.put("desc", "绑定成功！");
        } else {
            throw new RuntimeException(
                    "com.wfj.service.impl.MemberCardServiceImpl.bindMemberCard：绑定卡操作，不符合的绑定卡类型！");
        }
        logger.info("end com.wfj.service.impl.MemberCardServiceImpl.bindMemberCard(),return:"
                + returnMap);
        return returnMap;
    }

    /**
     * 解绑实体卡
     *
     * @param paraMap
     * @return
     */
    @Transactional
    public Map<String, Object> cardCancleBind(Map<String, Object> paraMap) throws Exception {
        logger.info("start com.wfj.service.impl.MemberCardServiceImpl.cardCancleBind(),para:"
                + paraMap.toString());
        String storeCode = paraMap.get("storeCode") + "";
        String openid = paraMap.get("openid") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeCode", storeCode);
        paramMap.put("openid", openid);
        List<MemberInfoReturnDto> returnDtoList = memberInfoMapper
                .selectMemberAndCardInfoListByParam(paramMap);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (returnDtoList.size() == 1) {
            MemberInfoReturnDto returnDto = returnDtoList.get(0);
            MemberCard memberCard = new MemberCard();
            memberCard.setStoreCode(storeCode);
            memberCard.setMemberCode(returnDto.getMemberCode());
            memberCard.setCardCode(returnDto.getCardCode());
            memberCard.setStatus(1);
            int i = memberCardMapper.updateByParaSelective(memberCard);
            if (i != 1) {
                throw new RuntimeException(
                        "com.wfj.service.impl.MemberCardServiceImpl.cardCancleBind：解绑卡时操作数据库失败！");
            }
        }

        returnMap.put("success", "true");
        returnMap.put("desc", "解绑成功！");

        logger.info("end com.wfj.service.impl.MemberCardServiceImpl.cardCancleBind(),return:"
                + returnMap);
        return returnMap;
    }

    public MemberCard findMemberCardByParam(Map<String, Object> paramMap) {
        MemberCard dto = null;
        List<MemberCard> memberCards = memberCardMapper.selectListByParam(paramMap);
        if (memberCards != null && memberCards.size() == 1) {
            dto = memberCards.get(0);
        }
        return dto;
    }
}
