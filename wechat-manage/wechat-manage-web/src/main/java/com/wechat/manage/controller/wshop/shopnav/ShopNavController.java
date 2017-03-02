package com.wechat.manage.controller.wshop.shopnav;


import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.utils.Common;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kongqf
 * @create 2017-01-17
 */
@Controller
@RequestMapping("/wshopnav")
public class ShopNavController extends BaseController {
    @RequestMapping("/list")
    public String list(Model model) {
        //model.addAttribute("res", findByRes());
        return Common.BACKGROUND_PATH + "/wshop/shopnav/list";
    }

    @RequestMapping("/navpreview")
    public String navpreview(Model model) {
        //model.addAttribute("res", findByRes());
        return Common.BACKGROUND_PATH + "/wshop/shopnav/shopnav";
    }
}
