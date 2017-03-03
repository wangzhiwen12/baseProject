package com.wechat.manage.service.wechat.impl;


import com.wechat.manage.service.util.Signs;
import com.wechat.manage.service.wechat.intf.ICartTicketService;
import com.wechat.manage.utils.HttpUtil;
import com.wechat.manage.utils.RedisUtil;
import com.wechat.manage.utils.StringUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by XS on 2016/12/20.
 */
@Service
public class CartTicketServiceImpl implements ICartTicketService {
    private Logger logger = Logger.getLogger(CartTicketServiceImpl.class);
    @Autowired
    private RedisUtil redisUtil;

    private static String WX_="W_X_";
    private static String WX_T_="W_X_T_";


    //测试用
    //String access_token="n3TJ7QGkVOnCKCiH1LweRs34CVCLwLPAzfRHFxyC8-VHYnOab5NEcvLZ_V99Ru4vLh4ayJyDJCpqod8Kaq0PFhz4U55Lu2ohsW9uhu2vKtKEtKjomrexzGRLc9b9PgO5VUCfABAPOW";
    //String url="http://wechat.wangfujing.com/notebook/cartTicket.html";


    public Map<String, String> getConfigInfo(String url,String access_token){
        logger.info("getConfigInfo() 开始");
        logger.info("参数:access_token"+ access_token+"    url:"+url);
        Map<String, String>   mapsign=new HashMap<String, String>();
        if(StringUtils.isBlank(url)){
            return mapsign;
        }
        if(StringUtils.isBlank(access_token)){
            return mapsign;
        }
        String type="jsapi";
        String api_ticket=  getApi_ticket(access_token,type);

        if(StringUtils.isNotEmpty(api_ticket)){
            mapsign=  getSign(api_ticket,url);
        }
        logger.info("getConfigInfo() 结束");
        return mapsign;
    }











    /**
     * 获取api_ticket
     * @param access_token,type:jsapi/wx_card   1.jsapi JSSDK用 2.wx_card 卡券用
     * @return api_ticket
     *   api_ticket 是用于调用卡券相关接口的临时票据，有效期为 7200 秒
     */
    public String getApi_ticket(String access_token,String type) {
        logger.info("getApi_ticket() 开始");
        /*
        * http请求方式: GET
            https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=wx_card
        * */
        String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket";
        Map<String,String> map=new HashMap<String, String>();
        map.put("access_token",access_token);
        map.put("type",type);
        String result="";
        String ticket="";
        try{
            String kticketa=redisUtil.get(WX_T_+type,"");
            // access_token 不存在或已变 从新获取api_ticket
            if("".equals(kticketa) || !kticketa.equals(access_token)){
                logger.info("access_token 不存在或与缓存不一致");
                logger.info("从新生成Api_ticket 类型为："+type);
                result= HttpUtil.sendGet(url,map);
                logger.info(" 生成Api_ticket结束，调微信接口返回值："+result);
                if(StringUtils.isNotEmpty(result)){
                    JSONObject newJson = JSONObject.fromObject(result);
                    if (newJson.getString("errcode").equals("0")) {
                        ticket = newJson.getString("ticket");

                        redisUtil.set(WX_+type,newJson.getString("ticket").toString(),7200);
                        logger.info(WX_+type+"放入redis"+newJson.getString("ticket").toString());
                        redisUtil.set(WX_T_+type,access_token,7200);
                        logger.info(WX_T_+type+"放入redis"+access_token);

                    }
                }else {
                    logger.error("获取Api_ticket失败  ");
                    logger.error("access_token: "+access_token+"    type:"+type);
                }
            }else{
                String kticket=redisUtil.get(WX_+type,"");
                if(StringUtils.isBlank(kticket)){
                    result= HttpUtil.sendGet(url,map);
                    if(StringUtils.isNotEmpty(result)){
                        JSONObject newJson = JSONObject.fromObject(result);
                        if (newJson.getString("errcode").equals("0")) {
                            ticket = newJson.getString("ticket");
                            redisUtil.set(WX_+type,newJson.getString("ticket").toString(),7200);
                            logger.info(WX_+type+"放入redis"+newJson.getString("ticket").toString());
                            redisUtil.set(WX_T_+type,access_token,7200);
                            logger.info(WX_T_+type+"放入redis"+access_token);
                        }
                    }else {
                        logger.error("获取Api_ticket失败  ");
                        logger.error("access_token: "+access_token+"    type:"+type);
                    }
                }else {
                    return kticket;
                }
            }






        }catch (Exception E){
            E.printStackTrace();
            logger.error(E.getMessage());
        }
        logger.info("getApi_ticket() 结束 ticket:"+ticket);
        return ticket;
    }

    /**
     * 获取签名
     * @param jsapi_ticket
     * @param url
     * @return
     */
    public Map<String, String> getSign(String jsapi_ticket,String url){
        logger.info("=======================getSign==start=================================");
        Map<String, String> map;
        map= Signs.sign(jsapi_ticket,url);
        logger.info("cart_:jsapi_ticket:"+jsapi_ticket);
        logger.info("cart_:url:"+url);
        for (Map.Entry entry:map.entrySet()) {
            logger.info("getSign_:"+entry.getKey()+":"+entry.getValue());
        }
        logger.info("cart_:jsapi_ticket:"+jsapi_ticket);
        logger.info("cart_:url:"+url);
        logger.info("=======================getCardSign==end=================================");
        return  map;
    }




    public  Map<String, String>  getCardSign(String api_ticket,String card_id,String accessToken){
        logger.info("=======================getCardSign==start=================================");
        Map<String, String> map;
        map=Signs.getCart(api_ticket,card_id);
        for (Map.Entry entry:map.entrySet()) {
            logger.info("cart_:"+entry.getKey()+":"+entry.getValue());
        }
        logger.info("cart_:card_id:"+card_id);
        logger.info("cart_:api_ticket:"+api_ticket);
        logger.info("cart_:accessToken: "+accessToken);
        logger.info("=======================getCardSign==end=================================");
        return  map;
    }

}
