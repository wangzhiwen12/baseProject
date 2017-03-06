package com.wechat.manage.controller.wechat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.exception.BleException;
import com.wechat.manage.pojo.system.entity.AppAccountInfo;
import com.wechat.manage.pojo.system.vo.StoreInfoDto;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.wechat.entity.*;
import com.wechat.manage.pojo.wechat.vo.WechatCardInfoDto;
import com.wechat.manage.pojo.wechat.vo.WxHomeDto;
import com.wechat.manage.pojo.wechat.vo.WxVIPCardDto;
import com.wechat.manage.service.util.WechatUtil;
import com.wechat.manage.service.wechat.impl.StoreInfoServiceImpl;
import com.wechat.manage.service.wechat.intf.IAppAccountInfoService;
import com.wechat.manage.service.wechat.intf.IPageManageService;
import com.wechat.manage.service.wechat.intf.PrivilegeService;
import com.wechat.manage.service.wechat.intf.WxVIPCardService;
import com.wechat.manage.utils.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author kongqf
 * @create 2016-12-30
 */
@Controller
@RequestMapping(value = {"/wechatpage"})
public class WechatPageController extends BaseController {
    Logger logerr = Logger.getLogger(WechatPageController.class);

    @Autowired
    private IPageManageService pageManageService;

    @Autowired
    private StoreInfoServiceImpl storeInfoService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private WxVIPCardService wxVIPCardService;

    @Autowired
    private WechatUtil wechatUtil;

    @Autowired
    private IAppAccountInfoService appAccountInfoService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 首页保存
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/addMemberHome"}, method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> addMemberHome(HttpServletRequest request, HttpServletResponse response) {
        JSONObject actualJson = null, headerJson = null;
        JSONArray navArrJson = null;
        String WxHeadHtml = "", WxHomeHtml = "";
        try {
            JSONObject jsonData = HttpUtil.HttpGetRequest(request);
            if (jsonData != null) {
                //JSONObject jsonData = jsono.getJSONObject("Data");
                JSONObject WxHomeHtmlM = JSONObject.parseObject(jsonData.getString("WxHomeHtmlM"));
                WxHeadHtml = jsonData.getString("WxHeadHtml");
                WxHomeHtml = jsonData.getString("WxHomeHtml");
                actualJson = WxHomeHtmlM.getJSONObject("actual");
                Set<String> keys = actualJson.keySet();
                for (String key : keys) {
                    if (key.contains("comheader_default")) {
                        headerJson = actualJson.getJSONObject(key);
                    }
                    if (key.contains("text-navigation")) {
                        navArrJson = (actualJson.getJSONObject(key)).getJSONArray("list");
                    }
                }
            }
        } catch (IOException ex) {
        }

        WxPageHome pageHome = new WxPageHome();

        UserBaseInfoDto curUser = getCurUserInfo();
        boolean flag = false;
        if (curUser != null) {
            String storeCode = curUser.getStoreCode();
            //String storeCode = "21011";
            WxPageHome wxpagehome = pageManageService.queryPageHomeByPara(storeCode);
            //PageManage pageParam = new PageManage();
            //PageManage pageManage = new PageManage();
            //pageParam.setStoreCode(curUser.getStoreCode());
            //pageParam.setPageType(1);
            //pageManage = pageManageService.queryPageManageByParam(pageParam);
            //if (pageManage != null) {
            WxPageHome pageHeader = getPageHomeH(headerJson, storeCode);
            pageHeader.setWxheadhtml(WxHeadHtml);
            pageHeader.setWxhomehtml(WxHomeHtml);
            pageHeader.setSid(wxpagehome.getSid());
            List<WxPageHomeNav> navList = getPageHomeNav(navArrJson, storeCode, 1);
            try {
                if (wxpagehome != null) {
                    WxPageHomeNav searchNav = new WxPageHomeNav();
                    searchNav.setPageType(1);
                    searchNav.setPageCode(storeCode);
                    List<WxPageHomeNav> pageHomeNavList = pageManageService.queryPageHomeNavByPara(searchNav);
                    if (pageHomeNavList != null && pageHomeNavList.size() > 0) {
                        for (WxPageHomeNav homeNav : pageHomeNavList) {
                            homeNav.setStatus(1);
                        }
                        /*flag = pageManageService.updatePageHomeNav(pageHomeNavList);
                        if (flag) {*/
                        flag = pageManageService.updatePageHome(pageHomeNavList, pageHeader, navList);
                        //}
                    }
                } else {
                    flag = pageManageService.addPageHome(pageHeader, navList);
                }
            } catch (BleException ex) {
                logerr.error(String.format("errorCode:%s,errorMsg:%s", ex.getCode(), ex.getMessage()));
                return ResultUtil.creComErrorResult(ex.getCode(), ex.getMessage());
            }
            //}
        }
        if (!flag) {
            return ResultUtil.creComErrorResult(ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getMemo());
        }

        return ResultUtil.creComSucResult("");
    }

    /**
     * 获取首页信息
     *
     * @return
     */
    @RequestMapping(value = {"/getMemberHomeInfo"}, method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getMemberHomeInfo() {
        UserBaseInfoDto curUser = getCurUserInfo();
        WxHomeDto wxHomeDto = new WxHomeDto();
        String storeCode = curUser.getStoreCode();
        // String storeCode="21013";
        WxPageHome pageHome = pageManageService.queryPageHomeByPara(storeCode);
        WxPageHomeNav searchNav = new WxPageHomeNav();
        searchNav.setPageType(1);
        searchNav.setPageCode(storeCode);
        List<WxPageHomeNav> pageHomeNav = pageManageService.queryPageHomeNavByPara(searchNav);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (pageHome != null) {
            //wxHomeDto.setSid(pageHome.getSid().toString());
            //wxHomeDto.setStoreCode(pageHome.getPageCode());
            //wxHomeDto.setStoreName(pageHome.getTitle());
            //wxHomeDto.setWxHeadHtml(pageHome.getWxheadhtml());
            //wxHomeDto.setWxHomeHtml(pageHome.getWxhomehtml());
            //wxHomeDto.setLogoFullUrl();
            Map<String, Object> mapPara = new HashMap<String, Object>();
            Map<String, Object> navListMap = new HashMap<String, Object>();
            //Map<String, Object> homeMap = new HashMap<String, Object>();
            TreeMap<String, Object> homeMap = new TreeMap<String, Object>();
            navListMap.put("list", pageHomeNav);
            homeMap.put("comheader_default", pageHome);
            homeMap.put("text-navigation_", navListMap);
            mapPara.put("actual", homeMap);
            dataMap.put("sid", pageHome.getSid());
            dataMap.put("LogoFullUrl", storeInfoService.getLogoUrl(storeCode));
            dataMap.put("WxHeadHtml", pageHome.getWxheadhtml());
            dataMap.put("WxHomeHtml", pageHome.getWxhomehtml());
            dataMap.put("WxHomeHtmlM", JsonUtil.getJSONString(mapPara));
        } else {
            //如果首次设置页面,传回门店的logo图
            dataMap.put("LogoFullUrl", storeInfoService.getLogoUrl(storeCode));
        }

        return ResultUtil.creComSucResult(dataMap);
    }

    /**
     * 查询特权内容
     *
     * @return
     */
    @RequestMapping(value = {"/getPrivilege"}, method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getPrivilege() {
        UserBaseInfoDto curUser = getCurUserInfo();
        String storeCode = curUser.getStoreCode();
        WxPageHome pageHome = pageManageService.queryPageHomeByPara(storeCode);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (pageHome != null) {
            dataMap.put("wxHeadHtml", pageHome.getWxheadhtml());
        }

        PrivilegeInfo privilegeInfo = privilegeService.findPrivilegeInfo(storeCode);
        if (privilegeInfo != null) {
            dataMap.put("showHead", privilegeInfo.getHeaderStatus());
            dataMap.put("privilege", privilegeInfo.getPrivilegeContent());
        }
        return ResultUtil.creComSucResult(dataMap);
    }

    /**
     * 发布特权内容
     *
     * @param privilege
     * @param showHead
     * @return
     */
    @RequestMapping(value = {"/alertPrivilege"}, method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> alertPrivilege(String privilege, String showHead) {
        UserBaseInfoDto curUser = getCurUserInfo();
        String storeCode = curUser.getStoreCode();

        PrivilegeInfo privilegeInfo = new PrivilegeInfo();
        privilegeInfo.setPrivilegeContent(privilege);
        privilegeInfo.setHeaderStatus(showHead);
        privilegeInfo.setStoreCode(storeCode);
        //根据门店号查询特权内容
        Map<String, Object> resultMap = privilegeService.alertPrivilege(privilegeInfo);
        return resultMap;
    }

    /**
     * 品牌会员卡
     *
     * @return
     */
    @RequestMapping(value = {"/getVipCardCfg"}, method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getWXVIPCard() {
        UserBaseInfoDto curUser = getCurUserInfo();
        String storeCode = curUser.getStoreCode();

        Map<String, Object> dataMap = new HashMap<String, Object>();
        WxVIPCard wxVIPCard = wxVIPCardService.findWxVIPCardByStoreCode(storeCode);
        if (wxVIPCard != null) {
            dataMap.put("headName", wxVIPCard.getHeadName());
            dataMap.put("headPictureUrl", wxVIPCard.getHeadPictureUrl());
            dataMap.put("wxHeadPictureUrl", wxVIPCard.getWxHeadPictureUrl());
            dataMap.put("isShowBrandCode", wxVIPCard.getBrandCodeStatus());
            dataMap.put("isShowOnlineCode", wxVIPCard.getOnlineCodeStatus());
        } else {
            dataMap.put("headName", "王府井会员卡");
            dataMap.put("headPictureUrl", "./images/wfjcard.jpg");
            dataMap.put("wxHeadPictureUrl", "");
            dataMap.put("isShowBrandCode", false);
            dataMap.put("isShowOnlineCode", false);
        }
        return ResultUtil.creComSucResult(dataMap);
    }

    @RequestMapping(value = {"/saveVipCardCfg"}, method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> saveVipCardCfg(WxVIPCardDto vipCardDto) {
        UserBaseInfoDto curUser = getCurUserInfo();
        String storeCode = curUser.getStoreCode();

        vipCardDto.setStoreCode(storeCode);
        Map<String, Object> map = wxVIPCardService.saveWxVIPCard(vipCardDto);
        return map;
    }

    /**
     * 页面 header
     *
     * @param object
     * @return
     */
    public WxPageHome getPageHomeH(JSONObject object, String pageCode) {
        WxPageHome pageHome = new WxPageHome();
        if (object != null) {
            if (object.containsKey("title")) {
                pageHome.setTitle(object.getString("title"));
            }
            if (object.containsKey("cardno")) {
                pageHome.setCardno(object.getString("cardno"));
            }
            if (object.containsKey("img")) {
                pageHome.setImg(object.getString("img"));
            }
            if (object.containsKey("template")) {
                pageHome.setTemplate(object.getString("template"));
            }
            pageHome.setPageCode(pageCode);
        }
        return pageHome;
    }

    /**
     * 页面导航
     *
     * @param objectArr
     * @param pageCode
     * @return
     */
    public List<WxPageHomeNav> getPageHomeNav(JSONArray objectArr, String pageCode, Integer pagaType) {
        List<WxPageHomeNav> navList = new ArrayList<WxPageHomeNav>();
        WxPageHomeNav pageHhomeNav = null;
        if (objectArr != null) {
            for (int i = 0; i < objectArr.size(); i++) {
                pageHhomeNav = new WxPageHomeNav();
                JSONObject object = objectArr.getJSONObject(i);
                if (object.containsKey("linkname")) {
                    pageHhomeNav.setLinkname(object.getString("linkname"));
                }
                if (object.containsKey("link")) {
                    pageHhomeNav.setLink(object.getString("link"));
                }
                if (object.containsKey("title")) {
                    pageHhomeNav.setTitle(object.getString("title"));
                }
                if (object.containsKey("picture")) {
                    pageHhomeNav.setPicture(object.getString("picture"));
                }
                pageHhomeNav.setPageType(pagaType);
                pageHhomeNav.setOrderby(i);
                pageHhomeNav.setPageCode(pageCode);
                navList.add(pageHhomeNav);
            }
        }
        return navList;
    }


    /**
     * Wx页面信息查询
     *
     * @param storeCode
     * @param pageType
     * @return
     */
    @RequestMapping(value = {"/getWxPageInfo"}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> findWxPageInfoByPara(String storeCode, String pageType) {
        WxPageHome pageHome = null;
        if (Constants.WXPAGETYPE1.equals(pageType)) {
            pageHome = pageManageService.queryPageHomeByStoreCode(storeCode);
            if (pageHome == null) {
                return ResultUtil.creComErrorResult(ComErrorCodeConstants.ErrorCode.PAGE_DATA_ISNULL.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.PAGE_DATA_ISNULL.getMemo());
            }
        }

        return ResultUtil.creComSucResult(pageHome);
    }


    /**
     * 创建会员卡
     *
     * @param
     * @return
     */
    @RequestMapping("CreateWXCard")
    @ResponseBody
    public Map<String, Object> CreateMemberCard(HttpServletRequest request, HttpServletResponse response) {
        try {
            JSONObject jsonData = HttpUtil.HttpGetRequest(request);
            if (jsonData != null) {
                //JSONObject object = JSONObject.parseObject(jsonData.getString("Data"));
                UserBaseInfoDto curUser = getCurUserInfo();
                System.out.println("1111111111111111");
                System.out.println(JsonUtil.getJSONString(jsonData));
                WechatCardInfoDto cardInfoDto = getWxCardInfo(jsonData);
                String storeCode = cardInfoDto.getStoreCode();
                Map<String, Object> searchMap = new HashMap<String, Object>();
                searchMap.put("storeCode", storeCode);
                WechatCardInfo cardInfo = pageManageService.queryCardInfoByStoreCode(searchMap);
                boolean flag = false;
                if (cardInfo != null) {
                    flag = pageManageService.updateWxCardInfo(cardInfoDto);
                } else {
                    flag = pageManageService.addWxCardInfo(cardInfoDto);
                }

                //更新公众号信息
                AppAccountInfo appAccountInfo = new AppAccountInfo();
                appAccountInfo.setStorecode(storeCode);
                appAccountInfo.setFiled1("0");

                //创建微信会员卡
                if (flag && cardInfoDto.getIswxvipcard()) {
                    appAccountInfo.setFiled1("1");
                    cardInfoDto.setCoverWxPic("http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGUuGTrkYQDu8FMJ5Wvp5XeFIhAgqBHCqZSPRD6uriageejdic9m6UKqibXAAKsWh22yqHTzWu6NSfjDA/0");
                    StoreInfoDto storeInfoDto = appAccountInfoService.getStoreInfo(null, storeCode);
                    JSONObject cardObject = generWxCardInfo(cardInfoDto, storeInfoDto.getAppId());
                    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                    String resultCard = wechatUtil.createCard(storeInfoDto.getAppId(), storeInfoDto.getSecret(),  gson.toJson(cardObject));
                    String card_id = "";

                    if (StringUtils.isNotEmpty(resultCard)) {
                        JSONObject resultObject = JSONObject.parseObject(resultCard);
                        if (resultObject.containsKey("errcode")) {
                            if ("0".equals(resultObject.getString("errcode"))) {
                                card_id = resultObject.getString("card_id");

                                JSONObject putCardObject = generWxPutCardInfo(cardInfoDto, card_id);
                                String resultPutCard = wechatUtil.putCard(storeInfoDto.getAppId(), storeInfoDto.getSecret(), JsonUtil.getJSONString(putCardObject));
                                if (StringUtils.isNotEmpty(resultPutCard)) {
                                    JSONObject resultPutObject = JSONObject.parseObject(resultPutCard);
                                    if ("0".equals(resultPutObject.getString("errcode"))) {
                                        String cardUrl = resultPutObject.getString("url");
                                        appAccountInfo.setCardId(card_id);
                                        appAccountInfo.setCardUrl(cardUrl);

                                        boolean isApp = appAccountInfoService.UpdateAppAccountInfoByStoreCode(appAccountInfo);
                                        if (isApp) {
                                            boolean isDelSuc = redisUtil.del("storeInfoDto" + storeInfoDto.getAppId());
                                            System.out.println("del redis:" + isDelSuc);
                                        }
                                    } else {

                                    }
                                }
                            } else {

                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
        }

        return ResultUtil.creComSucResult("");
    }


    /**
     * 微信卡信息
     *
     * @param object
     * @return
     */
    private WechatCardInfoDto getWxCardInfo(JSONObject object) {
        WechatCardInfoDto cardInfoDto = new WechatCardInfoDto();
        List<WxPageHomeNav> cardNav = new ArrayList<WxPageHomeNav>();
        if (object != null) {
            UserBaseInfoDto curUser = getCurUserInfo();
            if (curUser != null) {
                //cardInfoDto.setStoreCode("21011");
                cardInfoDto.setStoreCode(curUser.getStoreCode());
                cardInfoDto.setCreateUserid(curUser.getUserId());
                cardInfoDto.setStatus(0);
            }
            cardInfoDto.setCardBrandname(getJsonValueS(object, "CardBrandName"));
            cardInfoDto.setCardLogoPic(getJsonValueS(object, "CardLogoPic"));
            cardInfoDto.setFullCardLogoPic(getJsonValueS(object, "FullCardLogoPic"));
            cardInfoDto.setCardTitle(getJsonValueS(object, "CardTitle"));
            cardInfoDto.setCenterType(getJsonValueS(object, "CenterType"));
            cardInfoDto.setCardNoType(getJsonValueS(object, "CardNoType"));
            cardInfoDto.setCodeType(getJsonValueS(object, "CodeType"));
            cardInfoDto.setCoverType(getJsonValueS(object, "CoverType"));
            cardInfoDto.setCoverPic(getJsonValueS(object, "CoverPic"));
            cardInfoDto.setFullCoverPic(getJsonValueS(object, "FullCoverPic"));
            cardInfoDto.setCoverWxPic(getJsonValueS(object, "CoverWxPic"));
            cardInfoDto.setCoverColor(getJsonValueS(object, "CoverColor"));
            cardInfoDto.setCenterTitle(getJsonValueS(object, "CenterTitle"));
            cardInfoDto.setCenterSubTitle(getJsonValueS(object, "CenterSubTitle"));
            cardInfoDto.setCenterUrl(getJsonValueS(object, "CenterUrl"));
            cardInfoDto.setSupplyBonus(getJsonValueB(object, "SupplyBonus"));
            cardInfoDto.setSupplyCoupon(getJsonValueB(object, "SupplyCoupon"));
            cardInfoDto.setSupplyGrade(getJsonValueB(object, "SupplyGrade"));
            cardInfoDto.setSupplyBalance(getJsonValueB(object, "SupplyBalance"));
            cardInfoDto.setBalanceUrl(getJsonValueS(object, "BalanceUrl"));
            cardInfoDto.setBalanceName(getJsonValueS(object, "BalanceName"));
            cardInfoDto.setIsbonusdiscount(getJsonValueB(object, "IsBonusDiscount"));
            cardInfoDto.setIscoupondiscount(getJsonValueB(object, "IsCouponDiscount"));
            cardInfoDto.setBonusRule(getJsonValueS(object, "BonusRule"));
            cardInfoDto.setDiscount(getJsonValueI(object, "Discount"));
            cardInfoDto.setPrerogative(getJsonValueS(object, "Prerogative"));
            cardInfoDto.setDescription(getJsonValueS(object, "Description"));
            //cardInfoDto.setBonusRule(getJsonValueS(object, "CustomCell1"));
            cardInfoDto.setNotice(getJsonValueS(object, "Notice"));
            cardInfoDto.setQuantity(getJsonValueI(object, "Quantity"));
            cardInfoDto.setCustomUrlName(getJsonValueS(object, "CustomUrlName"));
            cardInfoDto.setCustomUrl(getJsonValueS(object, "CustomUrl"));
            cardInfoDto.setCustomUrlSubTitle(getJsonValueS(object, "CustomUrlSubTitle"));
            cardInfoDto.setPromotionUrlName(getJsonValueS(object, "PromotionUrlName"));
            cardInfoDto.setPromotionUrl(getJsonValueS(object, "PromotionUrl"));
            cardInfoDto.setPromotionUrlSubTitle(getJsonValueS(object, "PromotionUrlSubTitle"));
            cardInfoDto.setWxcardurl(getJsonValueS(object, "WxCardUrl"));
            cardInfoDto.setIswxvipcard(getJsonValueB(object, "IsWxVipCard"));
            List<WxPageHomeNav> navList = new ArrayList<WxPageHomeNav>();
            WxPageHomeNav cell1 = getWxCardNav(getJsonValueS(object, "CustomCell1Obj"), cardInfoDto.getStoreCode(), 2, 1);
            if (cell1 != null) {
                navList.add(cell1);
            }
            cardInfoDto.setCustomCell(navList);
        }

        return cardInfoDto;
    }


    private String getJsonValueS(JSONObject object, String Key) {
        String value = "";
        if (object.containsKey(Key)) {
            value = object.getString(Key);
        }
        return value;
    }

    private boolean getJsonValueB(JSONObject object, String Key) {
        boolean value = false;
        if (object.containsKey(Key)) {
            value = object.getBoolean(Key);
        }
        return value;
    }

    private Integer getJsonValueI(JSONObject object, String Key) {
        Integer value = 0;
        if (object.containsKey(Key)) {
            value = object.getInteger(Key);
        }
        return value;
    }

    public WxPageHomeNav getWxCardNav(String cellJson, String storeCode, Integer pagaType, Integer orderBy) {
        WxPageHomeNav cellNav = null;
        if (StringUtils.isNotEmpty(cellJson)) {
            cellNav = new WxPageHomeNav();
            JSONObject object = JSONObject.parseObject(cellJson);
            cellNav.setPageCode(storeCode);
            cellNav.setPageType(2);
            cellNav.setLink(getJsonValueS(object, "Url"));
            cellNav.setLinkname(getJsonValueS(object, "Name"));
            cellNav.setOrderby(orderBy);
        }
        return cellNav;
    }

    /**
     * 生成微信会员卡信息
     *
     * @param dto
     * @return
     */
    public JSONObject generWxCardInfo(WechatCardInfoDto dto, String state) {
        JSONObject wxCard = new JSONObject();
        JSONObject card = new JSONObject();
        JSONObject member_card = new JSONObject();
        JSONObject baseInfo = new JSONObject();
        JSONObject date_info = new JSONObject();
        JSONObject sku = new JSONObject();

        date_info.put("type", "DATE_TYPE_PERMANENT");
        sku.put("quantity", 100000000);
        //baseInfo.put("logo_url", dto.getFullCardLogoPic());
        baseInfo.put("logo_url", "http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGUuGTrkYQDu8FMJ5Wvp5XeFeOw2icl4zOo3wnpibpOJp6QTxA0aZQ52F8E3auR6zHtLdBMhrnHlODBw/0");
        baseInfo.put("brand_name", dto.getCardBrandname());
        baseInfo.put("code_type", dto.getCodeType());
        baseInfo.put("title", dto.getCardTitle());
        baseInfo.put("color", "Color010");
        //baseInfo.put("color", dto.getCoverColor());
        baseInfo.put("notice", dto.getNotice());
        baseInfo.put("service_phone", "010-88888888");
        baseInfo.put("description", dto.getDescription());
        baseInfo.put("date_info", date_info);
        baseInfo.put("sku", sku);
        baseInfo.put("get_limit", 1);
        baseInfo.put("use_custom_code", false);
        baseInfo.put("can_give_friend", true);
        baseInfo.put("custom_url_name", dto.getCustomUrlName());
        //baseInfo.put("custom_url", dto.getCustomUrl());
        baseInfo.put("custom_url", getWxUrl(state + ";5"));
        if (StringUtils.isNotEmpty(dto.getCustomUrlSubTitle())) {
            baseInfo.put("custom_url_sub_title", dto.getCustomUrlSubTitle());
        }
        //baseInfo.put("center_title", dto.getCenterTitle());
        //baseInfo.put("center_sub_title", dto.getCenterSubTitle());
        //baseInfo.put("center_url", dto.getCenterUrl());

        member_card.put("background_pic_url", dto.getCoverWxPic());
        //member_card.put("background_pic_url", "http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGUuGTrkYQDu8FMJ5Wvp5XeFIhAgqBHCqZSPRD6uriageejdic9m6UKqibXAAKsWh22yqHTzWu6NSfjDA/0");
        member_card.put("base_info", baseInfo);
        member_card.put("supply_bonus", dto.getSupplyBonus());
        member_card.put("supply_balance", dto.getSupplyBalance());
        member_card.put("prerogative", dto.getPrerogative());
        member_card.put("auto_activate", false);
        member_card.put("custom_field1", getCustomField("FIELD_NAME_TYPE_COUPON", state + ";4"));
        member_card.put("custom_field2", getCustomField("FIELD_NAME_TYPE_LEVEL", state + ";3"));
        //member_card.put("activate_url", getWxUrl("wx7aec942c6742752d;2"));
        member_card.put("activate_url", getWxUrl(state + ";2"));
        //member_card.put("custom_cell1", getCustomCell("绑定会员卡", "wx7aec942c6742752d;6"));
        if (dto.getCustomCell() != null && dto.getCustomCell().size() > 0) {
            member_card.put("custom_cell1", getCustomCell(dto.getCustomCell().get(0).getLinkname(), state + ";6"));
        }
        card.put("card_type", "MEMBER_CARD");
        card.put("member_card", member_card);

        wxCard.put("card", card);
        return wxCard;
    }

    /**
     * 会员卡自定义字段
     *
     * @param nameType
     * @param state
     * @return
     */
    public JSONObject getCustomField(String nameType, String state) {
        JSONObject cusObject = new JSONObject();
        cusObject.put("name_type", nameType);
        cusObject.put("url", getWxUrl(state));
        return cusObject;
    }

    public JSONObject getCustomCell(String name, String state) {
        JSONObject cusObject = new JSONObject();
        cusObject.put("name", name);
        cusObject.put("url", getWxUrl(state));
        return cusObject;
    }


    public String getWxUrl(String state) {
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7aec942c6742752d&redirect_uri=http://wechat.wangfujing.com/notebook/common/curMemberInfo.json&response_type=code&scope=snsapi_base&state=" + state + "#wechat_redirect";
        return url;
    }

    /**
     * 卡生成微信券投放信息
     *
     * @param dto
     * @param card_id
     * @return
     */
    public JSONObject generWxPutCardInfo(WechatCardInfoDto dto, String card_id) {
        JSONObject putCard = new JSONObject();
        JSONArray card_list = new JSONArray();
        JSONObject card = new JSONObject();
        //putCard.put("banner", "http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGUuGTrkYQDu8FMJ5Wvp5XeFIhAgqBHCqZSPRD6uriageejdic9m6UKqibXAAKsWh22yqHTzWu6NSfjDA/0");
        putCard.put("banner", dto.getCoverWxPic());
        putCard.put("can_share", true);
        putCard.put("scene", "SCENE_NEAR_BY");
        card.put("card_id", card_id);
        //card.put("thumb_url", "http://mmbiz.qpic.cn/mmbiz_jpg/h5ibsHDUJmGUuGTrkYQDu8FMJ5Wvp5XeFIhAgqBHCqZSPRD6uriageejdic9m6UKqibXAAKsWh22yqHTzWu6NSfjDA/0");
        card.put("thumb_url", dto.getCoverWxPic());
        card_list.add(card);
        putCard.put("card_list", card_list);
        return putCard;
    }

    @RequestMapping(value = {"/getWxCardInfo"}, method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getWxCardInfo() {
        Map<String, Object> cardMap = new HashMap<String, Object>();
        Map<String, Object> cardNavMap = new HashMap<String, Object>();
        Map<String, Object> searchMap = new HashMap<String, Object>();
        WxPageHomeNav searchNav = new WxPageHomeNav();
        UserBaseInfoDto curUser = getCurUserInfo();
        WechatCardInfo cardInfo = null;
        if (curUser != null) {
            String storeCode = curUser.getStoreCode();
            //String storeCode = "21011";
            searchMap.put("storeCode", storeCode);
            cardInfo = pageManageService.queryCardInfoByStoreCode(searchMap);
            searchNav.setPageType(2);
            searchNav.setPageCode(storeCode);
            List<WxPageHomeNav> customCell = pageManageService.queryPageHomeNavByPara(searchNav);
            cardMap = getWxCardMap(cardInfo, storeCode);
            if (customCell != null && customCell.size() > 0) {
                cardNavMap = getWxCardCustomMap(customCell.get(0));
                cardMap.put("CustomCell1Obj", JSONObject.parseObject(JsonUtil.getJSONString(cardNavMap)));
                cardMap.put("CustomCell1", JsonUtil.getJSONString(cardNavMap));
            } else {

            }
        }
        return ResultUtil.creComSucResult(JSONObject.parseObject(JsonUtil.getJSONString(cardMap)));
    }


    public Map<String, Object> getWxCardMap(WechatCardInfo cardInfo, String storeCode) {
        Map<String, Object> cardMap = new HashMap<String, Object>();

        if (cardInfo != null) {
            cardMap.put("CardBrandName", cardInfo.getCardBrandname());
            cardMap.put("CardLogoPic", cardInfo.getCardLogoPic());
            cardMap.put("FullCardLogoPic", cardInfo.getFullCardLogoPic());
            cardMap.put("CardTitle", cardInfo.getCardTitle());
            cardMap.put("CenterType", cardInfo.getCenterType());
            cardMap.put("CardNoType", cardInfo.getCardNoType());
            cardMap.put("CodeType", cardInfo.getCodeType());
            cardMap.put("CoverType", cardInfo.getCoverType());
            cardMap.put("CoverPic", cardInfo.getCoverPic());
            cardMap.put("FullCoverPic", cardInfo.getFullCoverPic());
            cardMap.put("CoverWxPic", cardInfo.getCoverWxPic());
            cardMap.put("CoverColor", cardInfo.getCoverColor());
            cardMap.put("CenterTitle", cardInfo.getCenterTitle());
            cardMap.put("CenterSubTitle", cardInfo.getCenterSubTitle());
            cardMap.put("CenterUrl", cardInfo.getCenterUrl());
            cardMap.put("SupplyBonus", cardInfo.getSupplyBonus());
            cardMap.put("SupplyCoupon", cardInfo.getSupplyCoupon());
            cardMap.put("SupplyGrade", cardInfo.getSupplyGrade());
            cardMap.put("SupplyBalance", cardInfo.getSupplyBalance());
            cardMap.put("BalanceUrl", cardInfo.getBalanceUrl());
            cardMap.put("BalanceName", cardInfo.getBalanceName());
            cardMap.put("IsBonusDiscount", cardInfo.getBalanceName());
            cardMap.put("IsCouponDiscount", cardInfo.getIsbonusdiscount());
            cardMap.put("BonusRule", cardInfo.getBonusRule());
            cardMap.put("Discount", cardInfo.getDiscount());
            cardMap.put("Prerogative", cardInfo.getPrerogative());
            cardMap.put("Description", cardInfo.getDescription());
            cardMap.put("Notice", cardInfo.getNotice());
            cardMap.put("Quantity", cardInfo.getQuantity());
            cardMap.put("CustomUrlName", cardInfo.getCustomUrlName());
            cardMap.put("CustomUrl", cardInfo.getCustomUrl());
            cardMap.put("CustomUrlSubTitle", cardInfo.getCustomUrlSubTitle());
            cardMap.put("PromotionUrlName", cardInfo.getPromotionUrlName());
            cardMap.put("PromotionUrl", cardInfo.getPromotionUrl());
            cardMap.put("PromotionUrlSubTitle", cardInfo.getPromotionUrlSubTitle());
            cardMap.put("WxCardUrl", cardInfo.getWxcardurl());
            cardMap.put("IsWxVipCard", cardInfo.getIswxvipcard());
        } else {
            StoreInfoDto storeInfoDto = appAccountInfoService.getStoreInfo(null, storeCode);
            cardMap.put("CardBrandName", storeInfoDto.getStoreName());
            cardMap.put("CardLogoPic", storeInfoDto.getLogoWxPic());
            cardMap.put("FullCardLogoPic", storeInfoDto.getLogoWxPic());
            cardMap.put("IsWxVipCard", true);
        }
        StoreInfoDto storeInfoDto = appAccountInfoService.getStoreInfo(null, storeCode);

        return cardMap;
    }

    public Map<String, Object> getWxCardCustomMap(WxPageHomeNav nav) {
        Map<String, Object> cardNavMap = new HashMap<String, Object>();
        if (nav != null) {
            cardNavMap.put("Name", nav.getLinkname());
            cardNavMap.put("Url", nav.getLink());
        }
        return cardNavMap;
    }
}
