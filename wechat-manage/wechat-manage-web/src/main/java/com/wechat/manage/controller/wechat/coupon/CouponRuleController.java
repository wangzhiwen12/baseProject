package com.wechat.manage.controller.wechat.coupon;

import com.alibaba.fastjson.JSONObject;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.pojo.wechat.entity.CouponRule;
import com.wechat.manage.service.wechat.intf.ICouponRuleService;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.HttpUtil;
import com.wechat.manage.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import net.sf.json.JSONObject;

/**
 * 优惠券规则
 *
 * @author kongqf
 * @create 2016-12-07
 */
@Controller
@RequestMapping("couponrule")
public class CouponRuleController extends BaseController {

    @Autowired
    private ICouponRuleService couponRuleService;

    @RequestMapping("/getinfo")
    public String list(Model model) {
        UserBaseInfoDto curUserInfo = getCurUserInfo();
        CouponRule couponRule = new CouponRule();
        couponRule.setStoreCode(curUserInfo.getStoreCode());
        List<CouponRule> list = couponRuleService.queryCouponInfo(couponRule);
        if (list != null && list.size() == 1) {
            model.addAttribute("coupon", list.get(0));
        }
        return Common.BACKGROUND_PATH + "/coupon/rule/add";
    }

    @RequestMapping(value = {"/queryCouponRuleInfo"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> queryDicList(Model model) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        UserBaseInfoDto curUserInfo = getCurUserInfo();
        CouponRule couponRule = new CouponRule();
        couponRule.setStoreCode(curUserInfo.getStoreCode());
        List<CouponRule> list = couponRuleService.queryCouponInfo(couponRule);

        if (list != null && list.size() > 0) {
            resultMap.put("success", true);
            resultMap.put("data", list.get(0));
        } else {
            resultMap.put("success", false);
            resultMap.put("data", "");
        }
        return resultMap;
    }

    /**
     * 新增券规则
     *
     * @param sid
     * @param NoLength
     * @param PrefixStr
     * @param StartNo
     * @param SuffixLength
     * @param colorselector_djq
     * @return
     */
    @RequestMapping("addEntity")
    @ResponseBody
    public String addEntity(String sid, String NoLength, String PrefixStr, String StartNo, String SuffixLength,
                            String colorselector_djq) {
        CouponRule couponRule = new CouponRule();
        UserBaseInfoDto curUserInfo = getCurUserInfo();
        couponRule.setStoreCode(curUserInfo.getStoreCode());
        couponRule.setNolength(new Integer(NoLength));
        couponRule.setPrefixstr(PrefixStr);
        couponRule.setStartno(StartNo);
        couponRule.setSuffixlength(SuffixLength);
        couponRule.setDjbackground(colorselector_djq);
        boolean flag = false;
        if (StringUtils.isNotEmpty(sid)) {
            couponRule.setSid(new Integer(sid));
            couponRule.setUpdateUserid(curUserInfo.getUserId());
            flag = couponRuleService.updateCouponInfo(couponRule);
        } else {
            couponRule.setCreateUserid(curUserInfo.getUserId());
            flag = couponRuleService.saveCouponInfo(couponRule);
        }

        if (flag) {
            return "success";
        } else {
            return "faile";
        }
    }

    @RequestMapping("addEntity2")
    @ResponseBody
    public String addEntity2(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        JSONObject object = null;
        try {
            object = HttpUtil.HttpGetRequest(request);
        } catch (IOException ex) {
        }
        CouponRule couponRule = (CouponRule) JSONObject.parseObject(object.toJSONString(), CouponRule.class);
        UserBaseInfoDto curUserInfo = getCurUserInfo();
        if (couponRule.getSid() != null) {
            couponRule.setSid(couponRule.getSid());
            couponRule.setUpdateUserid(curUserInfo.getUserId());
            flag = couponRuleService.updateCouponInfo(couponRule);
        } else {
            couponRule.setCreateUserid(curUserInfo.getUserId());
            couponRule.setStoreCode(curUserInfo.getStoreCode());
            flag = couponRuleService.saveCouponInfo(couponRule);
        }

        if (flag) {
            return "success";
        } else {
            return "failed";
        }
    }

}
