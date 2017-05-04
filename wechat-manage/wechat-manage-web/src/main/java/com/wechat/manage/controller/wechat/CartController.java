package com.wechat.manage.controller.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wechat.manage.pojo.cart.CalcResult;
import com.wechat.manage.pojo.cart.ModelResult;
import com.wechat.manage.pojo.cart.UserInfo;
import com.wechat.manage.utils.HttpClient;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XS on 2017/4/28.
 */
@Controller
public class CartController {
    private static Logger logger = Logger.getLogger(CartController.class);
    @RequestMapping("cart.htm")
    public  CalcResult cart(HttpServletRequest request,
                            HttpServletResponse response, HttpSession session){
        //String img = SysProperties.getProperty("img_url");
        CalcResult calcResult = new CalcResult();
        String result;
        String img_Url ="";
        ModelResult modelResult=new ModelResult();
        UserInfo userInfoObj=new UserInfo();
        String userId="202";
        userInfoObj = (UserInfo) session.getAttribute("ticket");
        if (userInfoObj!=null){
            if (userInfoObj.isLogin()) {
                userId = userInfoObj.getUID();
            } else {
                userId = userInfoObj.getCkey();
            }

            try{
                result = HttpClient.doPost("http://10.6.2.46:8098/wfj-trade-service/" + "salesCenter/getSalesPromotion/" + userId+".htm",
                        "");
                logger.info("********************"+result);
                if (StringUtils.isBlank(result)) {
                    // 返回数据为空
                    logger.info("取购物车数据为空");
                } else {
                    calcResult = JSON.parseObject(result, CalcResult.class);
                }
                modelResult.setCalcResult(calcResult);
                modelResult.setMenberwangfujing("http://member.wangfujingtest.com");
                modelResult.setWangfujingtest("www.wangfujing.Url");
                modelResult.setImgUrl(img_Url == null ? "" : img_Url);
                return calcResult;
            }catch (Exception e){

            }

        }

        modelResult.setCalcResult(null);
        modelResult.setMenberwangfujing("http://member.wangfujingtest.com");
        modelResult.setWangfujingtest("www.wangfujing.Url");
        modelResult.setImgUrl(img_Url == null ? "" : img_Url);

        return calcResult;
    }

    /**
     * 获取商品信息
     * @param
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping("/cart/showProList")
    public ModelResult showProList1(HttpServletRequest request,
                               HttpServletResponse response, HttpSession session) {
        CalcResult calcResult = new CalcResult();
        String result;
        String img_Url ="";
        ModelResult modelResult=new ModelResult();
        UserInfo userInfoObj=new UserInfo();
        String userId="202";
        userInfoObj = (UserInfo) session.getAttribute("ticket");
//

//            if (userInfoObj.isLogin()) {
//                userId = userInfoObj.getUID();
//            } else {
//                userId = userInfoObj.getCkey();
//            }

            try{
                result = HttpClient.doPost("http://10.6.2.46:8098/wfj-trade-service/" + "salesCenter/getSalesPromotion/" + userId+".htm",
                        "");
                logger.info("********************"+result);
                if (StringUtils.isBlank(result)) {
                    // 返回数据为空
                    logger.info("取购物车数据为空");
                } else {
                    calcResult = JSON.parseObject(result, CalcResult.class);
                }
                modelResult.setCalcResult(calcResult);
                modelResult.setMenberwangfujing("http://member.wangfujingtest.com");
                modelResult.setWangfujingtest("www.wangfujing.Url");
                modelResult.setImgUrl(img_Url == null ? "" : img_Url);
                return modelResult;
            }catch (Exception e){

            }
        return modelResult;
    }

    /**
     * 更新商品的勾选状态
     *
     * @return
     * @author
     */
    @RequestMapping("cart/delectProduct")
    public void selectProduct(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "marketId", required = true) String marketId,
            @RequestParam(value = "productSku", required = true) String productSku,
            @RequestParam(value = "isselected", required = true) String isselected,
            HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("ticket");
        String userid;
        String cartid;
        if (userInfo.isLogin()) {
            userid = userInfo.getUID();
            cartid = "";
        } else {
            userid = "";
            cartid = userInfo.getCkey();
        }
        // userid="112000";
        JSONObject jo = new JSONObject();
        jo.put("uuid", cartid);
        jo.put("uid", userid);
        JSONArray ja = new JSONArray();
        JSONObject jo1 = new JSONObject();
        jo1.put("supplyProductNo", productSku);
        jo1.put("isselected", isselected);
        jo1.put("partnumber", marketId);
        ja.add(jo1);
        jo.put("productNos", ja);
        String webHead ="http://10.6.2.46:8098/wfj-trade-service/";
        try {
            String result = HttpClient.doPost(webHead
                    + "onlineShop/updateProChick.htm", jo.toJSONString());
            logger.info("##################"+result);
            JSONObject res = JSONObject.parseObject(result);
            String resstr = res.getString("res");
            if ("1".equals(resstr)) {
                sendSuccess(resstr, response);
            } else {
                sendError("", response);
            }
        } catch (Exception e) {
            logger.error("更新商品的选中状态时出现异常", e);
            sendError("更新商品的选中状态时出现异常", response);
        }
    }














    private void sendSuccess(String msg,HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",true);
        map.put("msg",msg);
        send(map,response);
    }
    protected void sendError(String msg,HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",false);
        map.put("msg",msg);
        send(map,response);
    }
    protected void send(Map<String,Object> map,HttpServletResponse response){
        try {
            String json = JSON.toJSONString(map);
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(json);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
