package com.wechat.manage.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.utils.Common;

@Controller
@RequestMapping("/recoSys/")
public class RecoSys extends BaseController {

	@RequestMapping("/list")
	public String list(Model model, Integer id) throws Exception {
        model.addAttribute("res", findByRes());
        model.addAttribute("menuId", id);
        return Common.BACKGROUND_PATH + "/system/recoSys/list";
    }
}
