package com.wechat.manage.service.wechat.intf;

import java.util.Map;

/**
 * Created by XS on 2016/12/20.
 */
public interface ICartTicketService {

     String getApi_ticket(String access_token, String type);

     Map<String, String> getConfigInfo(String url, String access_token);

     Map<String, String>  getCardSign(String api_ticket, String card_id, String accessToken);


}
