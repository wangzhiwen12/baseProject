package com.wechat.manage.controller.wshop.cart;

import com.wechat.manage.utils.Common;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by XS on 2017/4/28.
 */
public class cartController {


    public String cart(){
        return Common.BACKGROUND_PATH + "/wshop/product/list";
    }
}
