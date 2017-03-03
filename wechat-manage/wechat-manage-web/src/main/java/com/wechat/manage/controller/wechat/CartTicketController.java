package com.wechat.manage.controller.wechat;


import com.wechat.manage.pojo.system.vo.ReturnDto;
import com.wechat.manage.pojo.system.vo.StoreInfoDto;
import com.wechat.manage.pojo.wechat.vo.CardExt;
import com.wechat.manage.service.util.WechatUtil;
import com.wechat.manage.service.wechat.intf.IAppAccountInfoService;
import com.wechat.manage.service.wechat.intf.ICartTicketService;
import com.wechat.manage.utils.JsonUtil;
import com.wechat.manage.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by XS on 2016/12/20.
 */
@Controller
@RequestMapping(value = "/wechat")
public class CartTicketController {

    @Autowired
    private ICartTicketService cartTicketService;
    @Autowired
    private WechatUtil wechatUtil;
    @Autowired
    private IAppAccountInfoService appAccountInfoService;

    @ResponseBody
    @RequestMapping(value = "/getSing", method = {RequestMethod.GET, RequestMethod.POST})
    public ReturnDto getSing(String url, String access_token) {
        ReturnDto ro = new ReturnDto();
        Map<String, String> map = cartTicketService.getConfigInfo(url, access_token);
        ro.setCode("0");
        ro.setObj(map);
        return ro;
    }

    @ResponseBody
    @RequestMapping(value = "/getSing2", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> getSing2(String url, String storePara) {
        // 1 通过门店接口获取appID,appSecret
        StoreInfoDto storeInfo = appAccountInfoService.getStoreInfo(storePara);
        System.out.println("storeInfo ================ " + storeInfo);
        String accessToken = wechatUtil.getAccessToken(storeInfo.getAppId(), storeInfo.getSecret());
        System.out.println("accessToken ================ " + accessToken);
        ReturnDto ro = new ReturnDto();
        Map<String, String> map = cartTicketService.getConfigInfo(url, accessToken);
//        map.put("appid",storeInfo.getAppId());
        ro.setCode("0");
        ro.setObj(map);
        return ResultUtil.creComSucResult(ro);// ro;
    }

    @ResponseBody
    @RequestMapping(value = "/getCartSing", method = {RequestMethod.GET,
            RequestMethod.POST})
    public ReturnDto getCartSing(String access_token, String card) {
        ReturnDto ro = new ReturnDto();
        Map<String, String> map;
        CardExt ce = new CardExt();
        String json = "";
        String api_ticket = cartTicketService.getApi_ticket(access_token, "wx_card");
        try {
            map = cartTicketService.getCardSign(api_ticket, card,access_token);
            ro.setCode("0");
            json = "nonce_str:" + map.get("nonce_str") + "timestamp:" + map.get("timestamp") + "signature:" + map.get("signature");
            ro.setObj(json);

        } catch (Exception e) {
            ro.setCode("1");
            e.printStackTrace();
        }

        //cardExt: '{"nonce_str":"db7e6469-2e87-4688-903d-e922072eccaa","timestamp": "1484042570", "signature":"43f036911ac466ec97a7ed2819373acd2d29fb78"}'

        return ro;
    }

    @ResponseBody
    @RequestMapping(value = "/getCartSing2", method = {RequestMethod.GET,
            RequestMethod.POST})
    public Map<String, Object> getCartSing2(String storePara) {
        // 1 通过门店接口获取appID,appSecret
        StoreInfoDto storeInfo = appAccountInfoService.getStoreInfo(storePara);
        System.out.println("storeInfo ================ " + storeInfo.toString());
        String accessToken = wechatUtil.getAccessToken(storeInfo.getAppId(), storeInfo.getSecret());
        System.out.println("accessToken ================ " + accessToken);

        ReturnDto ro = new ReturnDto();
        Map<String, String> map;
        Map<String, Object> maps=new HashMap<String, Object>();
        CardExt ce = new CardExt();
        String json = "";
        String api_ticket = cartTicketService.getApi_ticket(accessToken, "wx_card");
        try {
            map = cartTicketService.getCardSign(api_ticket,  storeInfo.getCardId(),accessToken);
            ro.setCode("0");
            ce.setNonce_str(map.get("nonce_str"));
            ce.setTimestamp(map.get("timestamp"));
            ce.setSignature(map.get("signature"));
            ro.setObj(ce);
            json= JsonUtil.getJSONString(ce);
           //String jsons = "nonce_str:" + map.get("nonce_str") + "timestamp:" + map.get("timestamp") + "signature:" + map.get("signature");
            maps.put("cardExts",json.toString());
            maps.put("cards", storeInfo.getCardId());
        } catch (Exception e) {
            ro.setCode("1");
            e.printStackTrace();
        }
        return ResultUtil.creComSucResult(maps);// ro;
    }

}
