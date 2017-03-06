package com.wechat.manage.service.wechat.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wechat.manage.exception.BleException;
import com.wechat.manage.mapper.wechat.PageManageMapper;
import com.wechat.manage.mapper.wechat.WechatCardInfoMapper;
import com.wechat.manage.mapper.wechat.WxPageHomeMapper;
import com.wechat.manage.mapper.wechat.WxPageHomeNavMapper;
import com.wechat.manage.pojo.wechat.entity.PageManage;
import com.wechat.manage.pojo.wechat.entity.WechatCardInfo;
import com.wechat.manage.pojo.wechat.entity.WxPageHome;
import com.wechat.manage.pojo.wechat.entity.WxPageHomeNav;
import com.wechat.manage.pojo.wechat.vo.WechatCardInfoDto;
import com.wechat.manage.service.wechat.intf.IPageManageService;
import com.wechat.manage.utils.ComErrorCodeConstants;
import com.wechat.manage.utils.RedisUtil;

/**
 * @author kongqf
 * @create 2016-12-30
 */
@Service
public class PageManageServiceImpl implements IPageManageService {
    Logger logger = Logger.getLogger(PageManageServiceImpl.class);

    @Autowired
    private PageManageMapper pageManageMapper;

    @Autowired
    private WxPageHomeMapper pageHomeMapper;

    @Autowired
    private WxPageHomeNavMapper pageHhomeNavMapper;

    @Autowired
    private WechatCardInfoMapper cardInfoMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询页面信息
     *
     * @param dto
     * @return
     */
    public PageManage queryPageManageByParam(PageManage dto) {
        return pageManageMapper.selectPageInfoByParam(dto);
    }

    public boolean addPageHome(WxPageHome homeHeader, List<WxPageHomeNav> homeNavList) {
        boolean flag = false;
        int count = pageHomeMapper.insertSelective(homeHeader);
        if (count == 1) {
            flag = true;
            count = 0;
        }
        if (flag) {
            if (homeNavList != null && homeNavList.size() > 0) {
                for (int i = 0; i < homeNavList.size(); i++) {
                    WxPageHomeNav pageHomeNav = homeNavList.get(i);
                    pageHomeNav.setOrderby(new Integer(i));
                    count = pageHhomeNavMapper.insertSelective(pageHomeNav);
                    if (count != 1) {
                        flag = false;
                        throw new BleException(ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getErrorCode(),
                                ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getMemo());
                    }
                }
            }
        }
        return flag;
    }

    public boolean updatePageHome(List<WxPageHomeNav> oHomeNavList, WxPageHome homeHeader, List<WxPageHomeNav> homeNavList) {
        if (oHomeNavList != null && oHomeNavList.size() > 0) {
            for (int i = 0; i < oHomeNavList.size(); i++) {
                int count = pageHhomeNavMapper.updateByPrimaryKeySelective(oHomeNavList.get(i));
                if (count != 1) {
                    throw new BleException(ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getErrorCode(),
                            ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getMemo());
                }
            }
        }

        boolean flag = false;
        int count = pageHomeMapper.updateByPrimaryKeySelective(homeHeader);
        if (count >= 0) {
            flag = true;
            count = 0;
        }
        if (flag) {
            if (homeNavList != null && homeNavList.size() > 0) {
                for (int i = 0; i < homeNavList.size(); i++) {
                    WxPageHomeNav pageHomeNav = homeNavList.get(i);
                    pageHomeNav.setOrderby(new Integer(i));
                    count = pageHhomeNavMapper.insertSelective(pageHomeNav);
                    if (count != 1) {
                        flag = false;
                        throw new BleException(ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getErrorCode(),
                                ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getMemo());
                    }
                }
            }
            try {
                boolean isSuccess = redisUtil.del("wxhome" + homeHeader.getPageCode());
                if (!isSuccess) {
                    logger.error("redis key: wxhome" + homeHeader.getPageCode() + " del failed!");
                }
            } catch (Exception ex) {
                logger.error(ex);
            }
        }
        return flag;
    }

    public boolean updatePageHomeNav(List<WxPageHomeNav> homeNavList) {
        boolean flag = true;
        if (homeNavList != null && homeNavList.size() > 0) {
            for (int i = 0; i < homeNavList.size(); i++) {
                int count = pageHhomeNavMapper.updateByPrimaryKeySelective(homeNavList.get(i));
                if (count != 1) {
                    flag = false;
                    throw new BleException(ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getErrorCode(),
                            ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getMemo());
                }
            }
        }
        return flag;
    }

    /**
     * 首页信息
     *
     * @param storeCode
     * @return
     */
    public WxPageHome queryPageHomeByPara(String storeCode) {
        WxPageHome wxPageHome = new WxPageHome();
        wxPageHome.setPageCode(storeCode);
        return pageHomeMapper.selectPageHomeByPara(wxPageHome);
    }

    /**
     * 首页信息
     *
     * @param storeCode
     * @return
     */
    public WxPageHome queryPageHomeByStoreCode(String storeCode) {
        String wxhome = redisUtil.get("wxhome" + storeCode, "0000");
        if ("0000".equals(wxhome)) {
            WxPageHome wxPageHome = new WxPageHome();
            wxPageHome.setPageCode(storeCode);
            return pageHomeMapper.selectPageHomeByStoreCode(wxPageHome);
        } else {
            return JSONObject.parseObject(wxhome, WxPageHome.class);
        }
    }

    /**
     * 导航信息
     *
     * @param pageHomeNav
     * @return
     */
    public List<WxPageHomeNav> queryPageHomeNavByPara(WxPageHomeNav pageHomeNav) {
        return pageHhomeNavMapper.selectPageHomeNavByPara(pageHomeNav);
    }

    /**
     * 新增微信会员卡信息
     *
     * @param dto
     * @return
     */
    public boolean addWxCardInfo(WechatCardInfoDto dto) {
        boolean flag = false;
        int count = cardInfoMapper.insertSelective(dto);
        if (count == 1) {
            flag = true;
            count = 0;
        }
        if (flag) {
            if (dto.getCustomCell() != null && dto.getCustomCell().size() > 0) {
                for (int i = 0; i < dto.getCustomCell().size(); i++) {
                    WxPageHomeNav pageHomeNav = dto.getCustomCell().get(i);
                    pageHomeNav.setOrderby(new Integer(i));
                    count = pageHhomeNavMapper.insertSelective(pageHomeNav);
                    if (count != 1) {
                        flag = false;
                        throw new BleException(ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getErrorCode(),
                                ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getMemo());
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 查询微信卡信息
     *
     * @param mapPara
     * @return
     */
    public WechatCardInfo queryCardInfoByStoreCode(Map<String, Object> mapPara) {
        return cardInfoMapper.selectCardInfoByStoreCode(mapPara);
    }

    /**
     * 更新会员卡信息
     *
     * @param dto
     * @return
     */
    public boolean updateWxCardInfo(WechatCardInfoDto dto) {
        boolean flag = true;
        int count = cardInfoMapper.updateByPrimaryKeySelective(dto);
        if (count != 1) {
            return false;
        } else {
            WxPageHomeNav delNav = new WxPageHomeNav();
            delNav.setStatus(1);
            delNav.setPageType(2);
            delNav.setPageCode(dto.getStoreCode());
            pageHhomeNavMapper.updateByStoreCode(delNav);
            if (dto.getCustomCell() != null && dto.getCustomCell().size() > 0) {


                for (int i = 0; i < dto.getCustomCell().size(); i++) {
                    WxPageHomeNav pageHomeNav = dto.getCustomCell().get(i);
                    pageHomeNav.setOrderby(new Integer(i));
                    count = pageHhomeNavMapper.insertSelective(pageHomeNav);
                    if (count != 1) {
                        flag = false;
                        throw new BleException(ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getErrorCode(),
                                ComErrorCodeConstants.ErrorCode.PAGE_ADD_FAILED_ERROR.getMemo());
                    }
                }
            }
        }
        return flag;
    }
}
