package com.wechat.manage.controller.wechat;

import com.wechat.manage.pojo.system.vo.StoreInfoDto;
import com.wechat.manage.pojo.wechat.entity.MemberCard;
import com.wechat.manage.pojo.wechat.vo.AccessTokenDto;
import com.wechat.manage.pojo.wechat.vo.MemberInfo;
import com.wechat.manage.pojo.wechat.vo.MemberInfoReturnDto;
import com.wechat.manage.pojo.wechat.vo.MemberInfoVo;
import com.wechat.manage.service.util.WechatUtil;
import com.wechat.manage.utils.PropertiesUtils;
import com.wechat.manage.utils.StringUtils;
import com.wechat.manage.service.wechat.intf.IAppAccountInfoService;
import com.wechat.manage.service.wechat.intf.MemberCardService;
import com.wechat.manage.service.wechat.intf.MemberInfoService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/common")
public class CommonController {
    private Logger logger = Logger.getLogger(CommonController.class);

    @Autowired
    private WechatUtil util;
    @Autowired
    private IAppAccountInfoService appAccountInfoService;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private MemberCardService memberCardService;

    @RequestMapping(value = "memberInfo", method = RequestMethod.GET)
    public void memberInfo(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "code", required = true) String code,
                           @RequestParam(value = "state", required = true) String state) {
        long starTime = System.currentTimeMillis();
        try {
            // 1 通过门店接口获取appID,appSecret
            StoreInfoDto storeInfo = appAccountInfoService.getStoreInfo(state);
            System.out.println("storeInfo ================ " + storeInfo);
            String appid = storeInfo.getAppId(); //"wx871d0104ae72e615";
            String secret = storeInfo.getSecret(); //"00e66c2772af76181745b6f5d92b5801";
            // 2 通过appID,appSecret获取access_token
            String accessToken = util.getAccessToken(appid, secret);
            System.out.println("accessToken ================ " + accessToken);
            System.out.println("code:" + code);
            // 3 通过code或取网页授权access_token(暂时不用)及openID
            AccessTokenDto atkDto = util.getOpenId(appid, secret, code);
            System.out.println("atkDto ================ " + atkDto);
            // 4 通过access_token,openID获取用户信息
            MemberInfo memberInfo = util.Openid_userinfo(atkDto.getOpenid(), appid, secret);
            System.out.println("memberInfo ================ " + memberInfo);
            String name = util.getURLEncoder(memberInfo.getNickname());
            String para = "&appId=" + appid + "&openId=" + atkDto.getOpenid() + "&secret=" + secret
                    + "&headimgurl=" + memberInfo.getHeadimgurl() + "&nickname=" +
                    name + "&registType=&unionId=" + memberInfo.getUnionid() + "&storeCode=" + storeInfo.getStoreCode();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String sendGet = getMemberInfo(appid, atkDto.getOpenid(), storeInfo.getStoreCode());
            long endTime = System.currentTimeMillis();
            System.out.println("------------------------------------" + (endTime - starTime));

            response.sendRedirect(sendGet + para);
            System.out.println("decoder ================ " + sendGet + para);
            PrintWriter out = response.getWriter();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "registMember", method = RequestMethod.GET)
    public void registMember(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "code", required = true) String code,
                             @RequestParam(value = "state", required = true) String state) {
        long starTime = System.currentTimeMillis();
        try {
            // 1 通过门店接口获取appID,appSecret
            StoreInfoDto storeInfo = appAccountInfoService.getStoreInfo(state);
            System.out.println("storeInfo ================ " + storeInfo);
            String appid = storeInfo.getAppId(); //"wx871d0104ae72e615";
            String secret = storeInfo.getSecret(); //"00e66c2772af76181745b6f5d92b5801";
            // 2 通过appID,appSecret获取access_token
            String accessToken = util.getAccessToken(appid, secret);
            System.out.println("accessToken ================ " + accessToken);
            System.out.println("code:" + code);
            // 3 通过code或取网页授权access_token(暂时不用)及openID
            AccessTokenDto atkDto = util.getOpenId(appid, secret, code);
            System.out.println("atkDto ================ " + atkDto);
            // 4 通过access_token,openID获取用户信息
            MemberInfo memberInfo = util.Openid_userinfo(atkDto.getOpenid(), appid, secret);
            System.out.println("memberInfo ================ " + memberInfo);
            String name = util.getURLEncoder(memberInfo.getNickname());
            String para = "&appId=" + appid + "&openId=" + atkDto.getOpenid() + "&secret=" + secret
                    + "&headimgurl=" + memberInfo.getHeadimgurl() + "&nickname=" +
                    name + "&registType=&unionId=" + memberInfo.getUnionid() + "&storeCode=" + storeInfo.getStoreCode();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String sendGet = PropertiesUtils.findPropertiesKey("registurl");
            ;
            long endTime = System.currentTimeMillis();
            System.out.println("------------------------------------" + (endTime - starTime));

            response.sendRedirect(sendGet + para);
            System.out.println("decoder ================ " + sendGet + para);
            PrintWriter out = response.getWriter();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取会员用户及会员卡信息(跳转页面)
     *
     * @param
     * @return
     * @throws Exception
     */
    private String getMemberInfo(String appid, String openid, String storeCode) throws Exception {
        logger.info("start com.wfj.controller.member.MemberInfoController.getMemberInfo(),para:{appid:" + appid + ",openid:" + openid + "}");
        String url = "", para = "", cardStatus = "";
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("appid", appid);
        paraMap.put("openid", openid);
        paraMap.put("storeCode", storeCode);
        Map<String, Object> returnMap = memberInfoService.getMemberInfo(paraMap);
        if ("0".equals(returnMap.get("code") + "")) {// 未注册、未实体绑卡 跳转至头像首页
            logger.info("com.wfj.controller.member.MemberInfoController.getMemberInfo:未注册、未实体绑卡");
            url = PropertiesUtils.findPropertiesKey("myMemberInfoInit");
            para = "cardType=0";
        } else if ("1".equals(returnMap.get("code") + "")) {
            MemberInfoReturnDto returnDto = new MemberInfoReturnDto();
            BeanUtils.copyProperties(returnDto, returnMap.get("obj"));
            returnDto.setQrcode(returnDto.getStoreCode() + returnDto.getMemberCode() + returnDto.getCardCode());

            String mobile = returnDto.getMobile();
            cardStatus = "1";
            para = "cardType=1&memberCode=" + returnDto.getMemberCode() + "&cardNo=" + returnDto.getCardCode() +
                    "&custType=" + returnDto.getCardLevel() + "&qrcode=" + returnDto.getQrcode() + "&mobile=" + mobile;

            Integer cardType = returnDto.getCardType();
            if (cardType != 1) {// 是否存在有实体卡,无实体卡
                url = PropertiesUtils.findPropertiesKey("myMemberInfo");
                para = para + "&entityCard=0&updatePWD=0";
            } else if (cardType == 1) {// 有实体卡
                url = PropertiesUtils.findPropertiesKey("myMemberInfo");
                if (StringUtils.isNotEmpty(mobile)) {//有手机号
                    para = para + "&unBund=0&updatePWD=0";
                } else {//没有手机号
                    para = para + "&unBund=0&boundPhone=0";
                }
            }
        }
        logger.info("end com.wfj.controller.member.MemberInfoController.getMemberInfo(),return:" + url + para);
        return url + para;
    }

    private String getMemberInfo_bak(String appid, String openid, String storeCode) throws Exception {
        logger.info("start com.wfj.controller.member.MemberInfoController.getMemberInfo(),para:{appid:" + appid + ",openid:" + openid + "}");
        String url = "";
        String para = "";
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("appid", appid);
        paraMap.put("openid", openid);
        paraMap.put("storeCode", storeCode);
        Map<String, Object> returnMap = memberInfoService.getMemberInfo(paraMap);
        if ("0".equals(returnMap.get("code") + "")) {// 未注册、未实体绑卡 跳转至头像首页
            logger.info("com.wfj.controller.member.MemberInfoController.getMemberInfo:未注册、未实体绑卡");
            url = PropertiesUtils.findPropertiesKey("myMemberInfoInit");
        } else if ("1".equals(returnMap.get("code") + "")) {
            MemberInfoReturnDto returnDto = new MemberInfoReturnDto();
            BeanUtils.copyProperties(returnDto, returnMap.get("obj"));
            returnDto.setQrcode(returnDto.getStoreCode() + returnDto.getMemberCode() + returnDto.getCardCode());

            String mobile = returnDto.getMobile();

            para = "memberCode=" + returnDto.getMemberCode() + "&cardNo=" + returnDto.getCardCode() +
                    "&custType=" + returnDto.getCardLevel() + "&qrcode=" + returnDto.getQrcode() + "&mobile=" + mobile;

            Integer cardType = returnDto.getCardType();
            if (cardType != 1) {// 是否存在有实体卡,无实体卡
                url = PropertiesUtils.findPropertiesKey("myMemberInfo");
                para = para + "&entityCard=0&updatePWD=0";
            } else if (cardType == 1) {// 有实体卡
                url = PropertiesUtils.findPropertiesKey("myMemberInfo");
                if (StringUtils.isNotEmpty(mobile)) {//有手机号
                    para = para + "&unBund=0&updatePWD=0";
                } else {//没有手机号
                    para = para + "&unBund=0&boundPhone=0";
                }
            }
        }
        logger.info("end com.wfj.controller.member.MemberInfoController.getMemberInfo(),return:" + url + para);
        return url + para;
    }

    /**
     * 新会员页面接口
     *
     * @param request
     * @param response
     * @param code
     * @param state
     */
    @RequestMapping(value = "wShopEntrance", method = RequestMethod.GET)
    public void wShopEntrance(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "code", required = true) String code,
                              @RequestParam(value = "state", required = true) String state){
        long starTime = System.currentTimeMillis();
        String appid = "", secret = "", openid = "", storePara = "", pageType = "";
        try {
            String arrPara[] = state.split(";");
            if (arrPara != null) {
                storePara = arrPara[0];
                pageType = arrPara[1];
                System.out.println("storePara:" + storePara + "pageType:" + pageType);
            }

            // 1 通过门店接口获取appID,appSecret
            StoreInfoDto storeInfo = appAccountInfoService.getStoreInfo(storePara);
            System.out.println("storeInfo ================ " + storeInfo);
            appid = storeInfo.getAppId();
            secret = storeInfo.getSecret();

            // 3 通过code或取网页授权access_token(暂时不用)及openID
            AccessTokenDto atkDto = util.getOpenId(appid, secret, code);
            System.out.println("atkDto ================ " + atkDto);
            openid = atkDto.getOpenid();

            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String url = getCurMemberInfo(storeInfo.getStoreCode(), openid, pageType, appid);
            //String url = "http://wechat.wangfujing.com/notebook/wechatShopPage/preview.shtml?openId="+openid+"&storeCode="+storeInfo.getStoreCode();
            long endTime = System.currentTimeMillis();
            System.out.println("------------------------------------" + (endTime - starTime));
            response.sendRedirect(url);
            System.out.println("decoder ================ " + url);
            PrintWriter out = response.getWriter();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新会员页面接口
     *
     */
    /*@RequestMapping(value = "curMemberInfo", method = RequestMethod.GET)
    public void curMemberInfo(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "code", required = true) String code,
                              @RequestParam(value = "state", required = true) String state) {
        long starTime = System.currentTimeMillis();
        String appid = "", secret = "", openid = "", storePara = "", pageType = "";
        try {
            String arrPara[] = state.split(";");
            if (arrPara != null) {
                storePara = arrPara[0];
                pageType = arrPara[1];
                System.out.println("storePara:" + storePara + "pageType:" + pageType);
            }

            // 1 通过门店接口获取appID,appSecret
            StoreInfoDto storeInfo = appAccountInfoService.getStoreInfo(storePara);
            System.out.println("storeInfo ================ " + storeInfo);
            appid = storeInfo.getAppId();
            secret = storeInfo.getSecret();

            // 3 通过code或取网页授权access_token(暂时不用)及openID
            AccessTokenDto atkDto = util.getOpenId(appid, secret, code);
            System.out.println("atkDto ================ " + atkDto);
            openid = atkDto.getOpenid();

            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String url = getCurMemberInfo(storeInfo.getStoreCode(), openid, pageType, appid);
            long endTime = System.currentTimeMillis();
            System.out.println("------------------------------------" + (endTime - starTime));
            response.sendRedirect(url);
            System.out.println("decoder ================ " + url);
            PrintWriter out = response.getWriter();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    @RequestMapping(value = "getCurMemberInfo_1", method = RequestMethod.POST)
    public MemberInfoVo getCurMemberInfo_1(String storeCode, String openid, String pageType, String appId){
        MemberInfoVo memberInfoVo = new MemberInfoVo();
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("openid", openid);
        paraMap.put("storeCode", storeCode);
        //MemberInfoReturnDto returnDto = memberInfoService.queryCurMemberInfo(paraMap);
        com.wechat.manage.pojo.wechat.entity.MemberInfo returnDto = memberInfoService.findMemberInfoByParam(paraMap);
        logger.info("===========MemberInfo:" + returnDto);
        StringBuilder sbPara = new StringBuilder();

        if (returnDto != null) {
            MemberCard memberCard = null;
            if (StringUtils.isNotEmpty(returnDto.getMemberCode())) {
                Map<String, Object> cardPara = new HashMap<String, Object>();
                cardPara.put("memberCode", returnDto.getMemberCode());
                cardPara.put("storeCode", storeCode);
                memberCard = memberCardService.findMemberCardByParam(cardPara);
                logger.info("===========memberCard:" + returnDto);
            }

            if ("1".equals(pageType)) {//我的会员信息
                String cardType = StringUtils.isNotEmpty(returnDto.getMemberCode()) ? "1" : "0";
                memberInfoVo.setCardType(cardType);
                memberInfoVo.setMemberCode(returnDto.getMemberCode());
                memberInfoVo.setMobile(returnDto.getMobile());
                memberInfoVo.setStoreCode(storeCode);
                memberInfoVo.setOpenId(openid);
                memberInfoVo.setAppId(appId);
                if (memberCard != null) {
                    memberInfoVo.setCardNo(memberCard.getCardCode());
                    memberInfoVo.setCardNo(memberCard.getCardCode());
                    memberInfoVo.setCustType(memberCard.getCardLevel());
                }
                logger.info("===========sbPara:" + sbPara.toString());
            }
        }
        return memberInfoVo;
    }


    /**
     * 获取会员用户信息
     *
     * @param storeCode
     * @param openid
     * @return
     * @throws Exception
     */
    public String getCurMemberInfo(String storeCode, String openid, String pageType, String appId) throws Exception {
        logger.info("===========pageType:" + pageType);
        //cardType-1:已注册,0:已领取未注册或未领取
        String url = "", para = "", cardType = "";

        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("openid", openid);
        paraMap.put("storeCode", storeCode);
        //MemberInfoReturnDto returnDto = memberInfoService.queryCurMemberInfo(paraMap);
        com.wechat.manage.pojo.wechat.entity.MemberInfo returnDto = memberInfoService.findMemberInfoByParam(paraMap);
        logger.info("===========MemberInfo:" + returnDto);
        StringBuilder sbPara = new StringBuilder();
        if (returnDto != null) {
            MemberCard memberCard = null;
            if (StringUtils.isNotEmpty(returnDto.getMemberCode())) {
                Map<String, Object> cardPara = new HashMap<String, Object>();
                cardPara.put("memberCode", returnDto.getMemberCode());
                cardPara.put("storeCode", storeCode);
                memberCard = memberCardService.findMemberCardByParam(cardPara);
                logger.info("===========memberCard:" + returnDto);
            }

            if ("1".equals(pageType)) {//我的会员信息
                url = PropertiesUtils.findPropertiesKey("MemberInfoUrl");
                cardType = StringUtils.isNotEmpty(returnDto.getMemberCode()) ? "1" : "0";
                logger.info("===========cardType:" + cardType);
                sbPara.append("cardType=" + cardType);
                sbPara.append("&memberCode=" + returnDto.getMemberCode());
                sbPara.append("&mobile=" + returnDto.getMobile());
                sbPara.append("&storeCode=" + storeCode);
                sbPara.append("&openId=" + openid);
                sbPara.append("&appId=" + appId);
                if (memberCard != null) {
                    sbPara.append("&qrcode=" + memberCard.getCardCode());
                    sbPara.append("&cardNo=" + memberCard.getCardCode());
                    sbPara.append("&custType=" + memberCard.getCardLevel());
                }
                logger.info("===========sbPara:" + sbPara.toString());
            } else if ("2".equals(pageType)) {//激活链接
                url = PropertiesUtils.findPropertiesKey("registurl3");
                sbPara.append("headimgurl=" + returnDto.getHeadimgurl());
                sbPara.append("&nickname=" + util.getURLEncoder(returnDto.getNickname()));
                sbPara.append("&storeCode=" + storeCode);
                sbPara.append("&registType=");
                sbPara.append("&mobile=" + returnDto.getMobile());
                sbPara.append("&openId=" + openid);
                logger.info("===========sbPara:" + sbPara.toString());
            } else if ("3".equals(pageType)) {//会员等级
                url = PropertiesUtils.findPropertiesKey("memberLevelUrl");
                sbPara.append("storeCode=" + storeCode);
                sbPara.append("&openId=" + openid);
                if (memberCard != null) {
                    sbPara.append("&qrcode=" + memberCard.getCardCode());
                    sbPara.append("&cardNo=" + memberCard.getCardCode());
                    sbPara.append("&custType=" + memberCard.getCardLevel());
                }
                logger.info("sbPara:" + sbPara.toString());
            } else if ("4".equals(pageType)) {//会员优惠券
                url = PropertiesUtils.findPropertiesKey("userCoupon");
                sbPara.append("storeCode=" + storeCode);
                sbPara.append("&openId=" + openid);
                logger.info("===========sbPara:" + sbPara.toString());
            } else if ("5".equals(pageType)) {//我的特权
                url = PropertiesUtils.findPropertiesKey("memberPrivilegeUrl");
                url = url + storeCode + ".html";
            } else if ("6".equals(pageType)) {//绑定会员卡
                url = PropertiesUtils.findPropertiesKey("memberBindUrl");
                sbPara.append("bindType=2");
                sbPara.append("&unionId=" + returnDto.getUnionid());
                sbPara.append("&appId=");
                sbPara.append("&openId=" + openid);
                sbPara.append("&storeCode=" + storeCode);
                sbPara.append("&registType=");
                logger.info("===========sbPara:" + sbPara.toString());
            }
        }
        return url + sbPara.toString();
    }
}
