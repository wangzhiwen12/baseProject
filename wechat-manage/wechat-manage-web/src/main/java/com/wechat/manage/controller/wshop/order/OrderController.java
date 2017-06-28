package com.wechat.manage.controller.wshop.order;


import com.wechat.manage.pojo.order.Order;
import com.wechat.manage.pojo.order.ProOrder;
import com.wechat.manage.service.wechat.intf.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by XS on 2017/5/3.
 */
@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    private OrderInfoService orderInfoService;


    @RequestMapping("/createOrder")
    @ResponseBody
    public Map createOrder(@RequestBody ProOrder order){

       // ProOrder order=new ProOrder();
        return   orderInfoService.createOrder(order);

    }
}
