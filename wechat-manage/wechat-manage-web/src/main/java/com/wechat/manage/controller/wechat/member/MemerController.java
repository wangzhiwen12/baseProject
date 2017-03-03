package com.wechat.manage.controller.wechat.member;


import com.wechat.manage.annotation.SystemLog;
import com.wechat.manage.mapper.system.StoreInfoMapper;
import com.wechat.manage.mapper.wechat.MemberInfoMapper;
import com.wechat.manage.pojo.system.entity.StoreInfo;
import com.wechat.manage.pojo.wechat.entity.MemberInfo;
import com.wechat.manage.pojo.wechat.entity.MemberInfoExtendsForMap;
import com.wechat.manage.pojo.wechat.entity.MemberInfoUpdate;
import com.wechat.manage.pojo.wechat.vo.ConponForMemReturn;
import com.wechat.manage.pojo.wechat.vo.CouponForMemDto;
import com.wechat.manage.pojo.wechat.vo.CouponForMemExtendDto;
import com.wechat.manage.service.wechat.intf.CouponInfoService;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.StringUtils;
import com.wechat.manage.utils.JsonUtil;
import com.wechat.manage.utils.RedisUtil;
import com.wechat.manage.vo.DataTableParams;
import com.wechat.manage.vo.DataTableResult;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/member"})
public class MemerController {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MemberInfoMapper memberInfoMapper;

    @Autowired
    CouponInfoService couponInfoService;


    @Inject
    private StoreInfoMapper storeInfoMapper;


    private Logger logger = Logger.getLogger(MemerController.class);

    @RequestMapping("list")
    public String listUI(Model model) throws Exception {
        return Common.BACKGROUND_PATH + "/coupon/member/list";
    }

    /**
     * 查询品牌会员
     *
     * @param param
     * @param dataTableParams
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getAllMemberInfo")
    public DataTableResult findMember(String param, DataTableParams dataTableParams) throws Exception {
        param = java.net.URLDecoder.decode(param, "UTF-8");
        Session session = SecurityUtils.getSubject().getSession();
        String storecode = redisUtil.get(Common.USER_STORE_K + session.getAttribute("userSessionId"), "");
        Map<String, Object> map = new HashMap<String, Object>();
        if (Common.isNotEmpty(param)) {
            map.put("param1", param);
        }
        map.put("storeCode", storecode);
        List<MemberInfo> memberInfos = memberInfoMapper.selectListByParam(map);
        List<MemberInfoExtendsForMap> list = new ArrayList<MemberInfoExtendsForMap>();

        if (memberInfos.size() > 0) {
            for (MemberInfo memberInfo : memberInfos) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("storeCode", storecode);
                List<StoreInfo> storeInfoList = storeInfoMapper.selectListByParam(paramMap);
                if (storeInfoList.size() > 0) {
                    memberInfo.setStoreCode(storeInfoList.get(0).getBusinessName());
                }

                list.add(com.alibaba.fastjson.JSON.parseObject(JSONObject.fromObject(memberInfo).toString(),
                        MemberInfoExtendsForMap.class));
            }
        }
        DataTableResult<MemberInfoExtendsForMap> dataTableResult = new DataTableResult<MemberInfoExtendsForMap>();
        dataTableResult.setsEcho(dataTableParams.getsEcho());
        dataTableResult.setAaData(list);
        dataTableResult.setiTotalDisplayRecords(list.size());
        dataTableResult.setiTotalRecords(list.size());
        return dataTableResult;

    }

    /**
     * 详情页跳转
     *
     * @param model
     * @param openid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "detailUI")
    public String detailUI(Model model, String openid) throws Exception {
        model.addAttribute("openid", openid);
        return Common.BACKGROUND_PATH + "/coupon/member/detail";
    }

    /**
     * 详情页跳转
     *
     * @param model
     * @param openid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "adjustmentUI")
    public String adjustmentUI(Model model, String openid) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (Common.isNotEmpty(openid)) {
            map.put("openid", openid);
        }
        List<MemberInfo> memberInfos = memberInfoMapper.selectListByParam(map);
        MemberInfoExtendsForMap memberInfo = new MemberInfoExtendsForMap();
        if (memberInfos.size() > 0) {
            memberInfo = com.alibaba.fastjson.JSON.parseObject(JsonUtil.getJSONString(memberInfos.get(0)),
                    MemberInfoExtendsForMap.class);
        }
        model.addAttribute("member", memberInfo);
        return Common.BACKGROUND_PATH + "/coupon/member/adjustment";
    }

    /**
     * 会员基础信息跳转
     *
     * @param model
     * @param openid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "basic_informationUI")
    public String basic_informationUI(Model model, String openid) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (Common.isNotEmpty(openid)) {
            map.put("openid", openid);
        }
        List<MemberInfo> memberInfos = memberInfoMapper.selectListByParam(map);
        MemberInfoExtendsForMap memberInfo = new MemberInfoExtendsForMap();
        if (memberInfos.size() > 0) {
            memberInfo = com.alibaba.fastjson.JSON.parseObject(JsonUtil.getJSONString(memberInfos.get(0)),
                    MemberInfoExtendsForMap.class);
        }
        model.addAttribute("member", memberInfo);
        return Common.BACKGROUND_PATH + "/coupon/member/basic_information";
    }

    /**
     * 电子优惠券
     *
     * @param model
     * @param openid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "electronic_couponsUI")
    public String electronic_couponsUI(Model model, String openid) throws Exception {
        int notUseNum = 0;//未使用
        int used = 0;//已使用
        int overdue = 0;//已过期
        Map<String, Object> querymap = new HashMap<String, Object>();
        if (Common.isNotEmpty(openid)) {
            querymap.put("openid", openid);
        }
        List<MemberInfo> memberInfos = memberInfoMapper.selectListByParam(querymap);
        String storeCode = null;
        if (memberInfos.size() > 0) {
            storeCode = memberInfos.get(0).getStoreCode();
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("openId", openid);
        paramMap.put("storeCode", storeCode);
        List<CouponForMemDto> list = couponInfoService.getCouponInfoForMem(paramMap);
        List<CouponForMemExtendDto> listExtend = new ArrayList<CouponForMemExtendDto>();
        for (CouponForMemDto couponForMemDto : list) {
            if (couponForMemDto.getCardStatus().equals("0")) {
                notUseNum = notUseNum + 1;
            } else if (couponForMemDto.getCardStatus().equals("1")) {
                used = used + 1;
            } else if (couponForMemDto.getCardStatus().equals("2")) {
                overdue = overdue + 1;
            }
            if (StringUtils.isNotEmpty(couponForMemDto.getEndTime())) {
                SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
                couponForMemDto.setEndTime(f1.format(f1.parse(couponForMemDto.getEndTime())));
            }
            if (StringUtils.isNotEmpty(couponForMemDto.getCollectionTime())) {
                SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss");
                couponForMemDto.setCollectionTime(f2.format(f2.parse(couponForMemDto.getCollectionTime())));
            }

            listExtend.add(com.alibaba.fastjson.JSON.parseObject(JsonUtil.getJSONString(couponForMemDto), CouponForMemExtendDto.class));
        }
        ConponForMemReturn conponForMemReturn = new ConponForMemReturn();
        conponForMemReturn.setCoupons(listExtend);
        conponForMemReturn.setNotUseNum(String.valueOf(notUseNum));
        conponForMemReturn.setUsed(String.valueOf(used));
        conponForMemReturn.setOverdue(String.valueOf(overdue));

        model.addAttribute("coupons", conponForMemReturn);
        return Common.BACKGROUND_PATH + "/coupon/member/electronic_coupons";
    }

    @ResponseBody
    @RequestMapping(value = "/editMemberInfo")
    @SystemLog(module = "会员管理", methods = "会员管理-修改会员")
    public String editStore(Model model, MemberInfoUpdate param) throws Exception {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setSid(param.getSid());
        memberInfo.setNickname(param.getNickname());
        memberInfo.setSex(param.getSex());
        memberInfo.setMobile(param.getMobile());
        memberInfo.setBrithday(param.getBrithday());
        memberInfo.setModifyRemarks(param.getRemarks());
        memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
        return "success";
    }
}
