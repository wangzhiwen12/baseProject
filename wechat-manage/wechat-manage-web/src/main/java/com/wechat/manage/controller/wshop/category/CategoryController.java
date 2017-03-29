package com.wechat.manage.controller.wshop.category;

import com.wechat.manage.utils.Common;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kongqf on 2017/3/29.
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @RequestMapping("/list")
    public String list(Model model) {
        //model.addAttribute("res", findByRes());
        return Common.BACKGROUND_PATH + "/wshop/shopnav/list";
    }
}
