package com.wechat.manage.controller.wshop.product;

import com.wechat.manage.utils.Common;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kongqf on 2017/3/29.
 */
@Controller
@RequestMapping("pro")
public class ProductController {

    /**
     * 商品列表页
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {
        //model.addAttribute("res", findByRes());
        return Common.BACKGROUND_PATH + "/wshop/product/list";
    }
}
