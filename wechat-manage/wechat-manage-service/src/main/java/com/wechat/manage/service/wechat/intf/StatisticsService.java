package com.wechat.manage.service.wechat.intf;

import com.wechat.manage.pojo.wechat.vo.CumulateUserDto;
import com.wechat.manage.pojo.wechat.vo.StatisticsAllUserDto;
import com.wechat.manage.pojo.wechat.vo.StatisticsUserDto;

import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-12-01 0001.
 */
public interface StatisticsService {


    /**
     * 获取净增增加数量
     * @param paramMap
     * @return
     * @throws Exception
     */
    List<StatisticsUserDto> getUserSummary(Map<String, Object> paramMap) throws Exception;

    /**
     * 获取用户累积关注数量
     * @param paramMap
     * @return
     * @throws Exception
     */
    List<CumulateUserDto> getUserCumulatte(Map<String, Object> paramMap) throws Exception;


    List<StatisticsAllUserDto> getCurveData1(Map<String, Object> paramMap) throws Exception;
















}
