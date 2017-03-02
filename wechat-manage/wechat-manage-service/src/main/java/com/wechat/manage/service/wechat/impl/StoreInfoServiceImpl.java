package com.wechat.manage.service.wechat.impl;


import com.wechat.manage.mapper.system.AppAccountInfoMapper;
import com.wechat.manage.mapper.system.StoreInfoMapper;
import com.wechat.manage.pojo.system.entity.AppAccountInfo;
import com.wechat.manage.pojo.system.entity.StoreInfo;
import com.wechat.manage.pojo.system.vo.ReturnDto;
import com.wechat.manage.pojo.system.vo.StoreInfoDto;
import com.wechat.manage.service.wechat.intf.IAppAccountInfoService;
import com.wechat.manage.service.wechat.intf.StoreInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-12-01 0001.
 */
@Service
public class StoreInfoServiceImpl implements StoreInfoService {

    private static Logger logger = Logger.getLogger(StoreInfoService.class);

    @Autowired
    private StoreInfoMapper storeInfoMapper;
    @Autowired
    private AppAccountInfoMapper appAccountInfoMapper;
    @Autowired
    private IAppAccountInfoService appAccountInfoService;

    /**
     * 添加门店
     *
     * @param
     * @return
     */
    @Transactional
    public ReturnDto addStore(StoreInfo storeInfo) throws Exception {
        logger.debug("start com.wfj.service.impl.StoreInfoServiceImpl.addStore(),para:" + storeInfo.toString());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeCode", storeInfo.getStoreCode());
        List<StoreInfo> storeInfoList = storeInfoMapper.selectListByParam(paramMap);

        Map<String, Object> appParam = new HashMap<String, Object>();
        appParam.put("storecode", storeInfo.getStoreCode());
        appParam.put("delFlag", "0");
        List<AppAccountInfo> appAccountInfoList = appAccountInfoMapper.selectListByParam(appParam);
        ReturnDto returnDto = new ReturnDto();

        if (appAccountInfoList.size() == 0) {
            AppAccountInfo appAccountInfo = new AppAccountInfo();
            appAccountInfo.setAppid(storeInfo.getAppid());
            appAccountInfo.setAppsecret(storeInfo.getAppsecret());
            appAccountInfo.setStorecode(storeInfo.getStoreCode());
            appAccountInfo.setDelFlag(0);
            int i = appAccountInfoMapper.insertSelective(appAccountInfo);
            if (i == 1) {
                returnDto.setCode("1");
                returnDto.setDesc("添加成功！");
            } else {
                returnDto.setCode("0");
                returnDto.setDesc("添加失败！");
                throw new RuntimeException("com.wfj.service.impl.StoreInfoServiceImpl.addStore:添加门店操作数据库失败！");
            }
        }


        if (storeInfoList.size() == 0) {
            int i = storeInfoMapper.insertSelective(storeInfo);
            if (i == 1) {
                returnDto.setCode("1");
                returnDto.setDesc("添加成功！");
            } else {
                returnDto.setCode("0");
                returnDto.setDesc("添加失败！");
                throw new RuntimeException("com.wfj.service.impl.StoreInfoServiceImpl.addStore:添加门店操作数据库失败！");
            }
        } else {
            returnDto.setCode("0");
            returnDto.setDesc("门店编码已经存在！");
        }

        logger.debug("end com.wfj.service.impl.StoreInfoServiceImpl.addStore(),return:" + returnDto);
        return returnDto;
    }

    /**
     * 修改门店
     *
     * @param storeInfo
     * @return
     * @throws Exception
     */
    @Transactional
    public ReturnDto editStore(StoreInfo storeInfo) throws Exception {
        logger.debug("start com.wfj.service.impl.StoreInfoServiceImpl.editStore,para:" + storeInfo.toString());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeCode", storeInfo.getStoreCode());
        List<StoreInfo> storeInfoList = storeInfoMapper.selectListByParam(paramMap);
        int updateStatus = storeInfoMapper.updateByParaSelective(storeInfo);


        AppAccountInfo appAccountInfo = new AppAccountInfo();
        ReturnDto returnDto = new ReturnDto();
        if (storeInfoList.size() == 1) {
            storeInfoMapper.updateByParaSelective(storeInfo);
            appAccountInfo.setAppid(storeInfo.getAppid());
            appAccountInfo.setAppsecret(storeInfo.getAppsecret());
            appAccountInfo.setDelFlag(0);
            appAccountInfo.setStorecode(storeInfo.getStoreCode());
            appAccountInfoMapper.updateByStorecodeSelective(appAccountInfo);
            returnDto.setCode("1");
            returnDto.setDesc("修改成功！");
        } else {
            returnDto.setCode("0");
            returnDto.setDesc("修改的门店不存在！");
        }
        if (updateStatus != 1) {
            returnDto.setCode("0");
            returnDto.setDesc("修改图片地址错误");
        }

        logger.debug("end com.wfj.service.impl.StoreInfoServiceImpl.editStore,return:" + returnDto.toString());
        return returnDto;
    }

    /**
     * 批量删除门店
     *
     * @param
     * @return
     */
    @Transactional
    public ReturnDto batchDelStore(List<String> storeCodeList) {
        logger.debug("start com.wfj.service.impl.StoreInfoServiceImpl.delBatchStore(),para:" + storeCodeList.toString());
        ReturnDto returnDto = new ReturnDto();
        int i = storeInfoMapper.batchDeleteByPara(storeCodeList);
        returnDto.setCode("1");
        returnDto.setDesc("批量删除成功！");
        logger.debug("end com.wfj.service.impl.StoreInfoServiceImpl.delBatchStore(),return:" + returnDto.toString());
        return returnDto;
    }

    /**
     * logo url
     *
     * @param storeCode
     * @return
     */
    public String getLogoUrl(String storeCode) {
        StoreInfoDto dto = appAccountInfoService.getStoreInfo(null, storeCode);
        if (dto != null) {
            return dto.getLogoPic();
        }
        return null;
    }
}
