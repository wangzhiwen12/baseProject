package com.wechat.manage.service.wechat.intf;

import com.wechat.manage.pojo.order.ProOrder;

import java.util.Map;

/**
 * Created by XS on 2017/5/4.
 */
public interface OrderInfoService {

     Map createOrder(ProOrder order);
}
