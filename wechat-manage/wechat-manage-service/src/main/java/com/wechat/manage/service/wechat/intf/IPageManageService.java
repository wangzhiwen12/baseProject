package com.wechat.manage.service.wechat.intf;


import com.wechat.manage.pojo.wechat.entity.PageManage;
import com.wechat.manage.pojo.wechat.entity.WechatCardInfo;
import com.wechat.manage.pojo.wechat.entity.WxPageHome;
import com.wechat.manage.pojo.wechat.entity.WxPageHomeNav;
import com.wechat.manage.pojo.wechat.vo.WechatCardInfoDto;

import java.util.List;
import java.util.Map;

/**
 * @author kongqf
 * @create 2016-12-30
 */
public interface IPageManageService {

    public PageManage queryPageManageByParam(PageManage dto);

    public boolean addPageHome(WxPageHome homeHeader, List<WxPageHomeNav> homeNavList);

    //public boolean updatePageHome(WxPageHome homeHeader, List<WxPageHomeNav> homeNavList);
    public boolean updatePageHome(List<WxPageHomeNav> oHomeNavList, WxPageHome homeHeader, List<WxPageHomeNav> homeNavList);

    public boolean updatePageHomeNav(List<WxPageHomeNav> homeNavList);

    public WxPageHome queryPageHomeByPara(String storeCode);

    public WxPageHome queryPageHomeByStoreCode(String storeCode);

    public List<WxPageHomeNav> queryPageHomeNavByPara(WxPageHomeNav pageHomeNav);

    public boolean addWxCardInfo(WechatCardInfoDto dto);

    public WechatCardInfo queryCardInfoByStoreCode(Map<String, Object> mapPara);

    public boolean updateWxCardInfo(WechatCardInfoDto dto);
}
