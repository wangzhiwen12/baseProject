package com.wechat.manage.service.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by XS on 2016/12/21.
 */
public class Signs {


    public static void main(String[] args) {
//        String jsapi_ticket = "jsapi_ticket";
//
//        // 注意 URL 一定要动态获取，不能 hardcode
//        String url = "http://example.com";
//        Map<String, String> ret = sign(jsapi_ticket, url);
//
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }


//        String api_ticket="IpK_1T69hDhZkLQTlwsAXwJq_GRGHyo4zGBx51O21gj47_eJi2kL5j8uqk0tT89phxf1HgSNRHOr8L7scbsNsw";
//        String timestamp="1482299716";
//        String card_id="pXgrswSCCuTclT7BR7mD-yIE4ej0";
//        String nonce_str="JOIJIWIOSDOIJFIWOE";
//        Map<String, String> map=getCart(api_ticket,card_id);
//        for (Map.Entry entry:map.entrySet()) {
//            System.out.println(entry);
//        }

//        String card_id="pXgrswetJB8yxkS5-U4ZeM3g9TpY";
//        String appSecret="IpK_1T69hDhZkLQTlwsAXy5TcqW-iwEBBNCVfimzBnvhn6lK-lbyGEtkw3uvwJXg0m4Rr3tkBX0aGQE2TJJnEQ";
        String card_id="psah_wPfMsJeoy9w1xvhK_oHxYoU";
        String nonce_str=  "8aa5ad79-a3f5-44ff-a886-1efeb22f22e7";
        String timestamp="1484145787";
        String appSecret="IpK_1T69hDhZkLQTlwsAX_6CrD1n04xB145Ls9GaQDbRMkiPSdFcOI1Dfbi9gRvSBlguCSo5MpQIilWeIQuXLg";
        System.out.println("@@@@@@@@@@@@@@@@@@@@"+getCartS(appSecret,card_id,timestamp,nonce_str));



    }



    /**
     *获取签名信息
     * @param jsapi_ticket
     * @param url
     * @return
     */
    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }



    public static  Map<String, String> getCart(String api_ticket,String card_id){
        Map<String, String> ret = new HashMap<String, String>();
        String signature = "";
        String timestamp = create_timestamp();
        String nonce_str = create_nonce_str();
        /*
        顺序
        timestamp=1404896688，card_id=pjZ8Yt1XGILfi-FUsewpnnolGgZk， api_ticket=ojZ8YtyVyr30HheH3CM73y7h4jJE ，nonce_str=jonyqin 则signature=sha1(1404896688jonyqinjonyqin_1434008071ojZ8YtyVyr30HheH3CM73y7h4jJE pjZ8Yt1XGILfi-FUsewpnnolGgZk)=6b81fbf6af16e856334153b39737556063c82689。
         */
        String str=timestamp+api_ticket+nonce_str+card_id;
        System.out.println(str);
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("signature",signature);
        ret.put("timestamp",timestamp);
        ret.put("nonce_str",nonce_str);

        return  ret;
    }


    public static  Map<String, String> getCartS(String api_ticket,String card_id,String timestamp,String nonce_str){
        Map<String, String> ret = new HashMap<String, String>();
        String signature = "";
        /*
        顺序
        timestamp=1404896688，card_id=pjZ8Yt1XGILfi-FUsewpnnolGgZk， api_ticket=ojZ8YtyVyr30HheH3CM73y7h4jJE ，nonce_str=jonyqin 则signature=sha1(1404896688jonyqinjonyqin_1434008071ojZ8YtyVyr30HheH3CM73y7h4jJE pjZ8Yt1XGILfi-FUsewpnnolGgZk)=6b81fbf6af16e856334153b39737556063c82689。
         */
        String str=timestamp+api_ticket+nonce_str+card_id;
        System.out.println(str);
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("signature",signature);
        ret.put("timestamp",timestamp);
        ret.put("nonce_str",nonce_str);

        return  ret;
    }

}
