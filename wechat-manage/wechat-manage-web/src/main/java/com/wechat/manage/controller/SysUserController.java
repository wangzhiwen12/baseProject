package com.wechat.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.wechat.manage.pojo.system.SysUser;
import com.wechat.manage.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SysUserController
 *
 * @author kongqf
 * @create 2016-12-24
 */
@Controller
@RequestMapping("/sysUserController")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/showUserToJspById/{userId}")
    public String showUser(Model model, @PathVariable("userId") Long userId) {
        SysUser user = this.sysUserService.getById(userId);
        model.addAttribute("user", user);
        return "showUser";
    }

    @RequestMapping("/showUserToJSONById/{userId}")
    @ResponseBody
    public SysUser showUser(@PathVariable("userId") Long userId) {
        SysUser user = sysUserService.getById(userId);
        return user;
    }

    @RequestMapping(value = {"/test"}, method = {RequestMethod.POST})
    @ResponseBody
    public SysUser test(@RequestBody String messageBody) {
        JSONObject object = new JSONObject();
        //try {
        //    object = HttpGetRequest(request);
        //} catch (IOException ex) {
        //}
        SysUser user = null;
        return user;
    }

    public JSONObject HttpGetRequest(HttpServletRequest request) throws IOException {
        StringBuilder sbParaInfo = new StringBuilder();
        BufferedReader br = new BufferedReader(
                new InputStreamReader((ServletInputStream) request.getInputStream()));
        String line = null;
        while ((line = br.readLine()) != null) {
            sbParaInfo.append(line);
        }
        JSONObject jsono = JSONObject.parseObject(sbParaInfo.toString());
        return jsono;
    }
}
