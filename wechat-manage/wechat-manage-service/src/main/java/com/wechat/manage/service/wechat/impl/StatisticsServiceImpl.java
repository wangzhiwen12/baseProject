package com.wechat.manage.service.wechat.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wechat.manage.pojo.wechat.vo.CumulateUserDto;
import com.wechat.manage.pojo.wechat.vo.StatisticsAllUserDto;
import com.wechat.manage.pojo.wechat.vo.StatisticsUserDto;
import com.wechat.manage.service.wechat.intf.StatisticsService;
import com.wechat.manage.utils.HttpUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaocj on 2016-12-07 0001.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {


    private static Logger logger = Logger.getLogger(StatisticsService.class);


    public List<StatisticsUserDto> getUserSummary(Map<String, Object> paramMap) throws Exception {

        String access_token = paramMap.get("access_token").toString();
        String begin_date = paramMap.get("begin_date").toString();
        String end_date = paramMap.get("end_date").toString();

        String userSummaryUrl = "https://api.weixin.qq.com/datacube/getusersummary?access_token=" + access_token;
        Map<Object, Object> map = new HashMap<Object, Object>();
        List<Object> list = new ArrayList<Object>();
        map.put("begin_date", begin_date);
        map.put("end_date", end_date);
        long time = System.currentTimeMillis();
        String result = HttpUtils.doPost(userSummaryUrl, JSON.toJSONString(map));
        if (result != null) {
            JSONObject jsonObject = JSON.parseObject(result);
            List<StatisticsUserDto> dtoList = JSON.parseArray(jsonObject.getString("list"), StatisticsUserDto.class);
            return dtoList;
        } else {
            return null;
        }
    }

    public List<CumulateUserDto> getUserCumulatte(Map<String, Object> paramMap) throws Exception {
        String access_token = paramMap.get("access_token").toString();
        String begin_date = paramMap.get("begin_date").toString();
        String end_date = paramMap.get("end_date").toString();

        String userSummaryUrl = "https://api.weixin.qq.com/datacube/getusercumulate?access_token=" + access_token;
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("begin_date", begin_date);
        map.put("end_date", end_date);
        String result = HttpUtils.doPost(userSummaryUrl, JSON.toJSONString(map));
        if (result != null) {
            JSONObject jsonObject = JSON.parseObject(result);
            List<CumulateUserDto> dtoList = JSON.parseArray(jsonObject.getString("list"), CumulateUserDto.class);
            return dtoList;
        } else {
            return null;
        }
    }

    public List<StatisticsAllUserDto> getCurveData1(Map<String, Object> paramMap) throws Exception {

        System.out.println("paramMap："+paramMap );
        List<StatisticsAllUserDto> dtoList = new ArrayList<StatisticsAllUserDto>();
        String access_token = paramMap.get("access_token").toString();
        String begin_date = paramMap.get("begin_date").toString();
        String end_date = paramMap.get("end_date").toString();

        String userSummaryUrl1 = "https://api.weixin.qq.com/datacube/getusersummary?access_token=" + access_token;
        String userSummaryUrl2 = "https://api.weixin.qq.com/datacube/getusercumulate?access_token=" + access_token;

        Map<Object, Object> map = new HashMap<Object, Object>();
        List<Object> list = new ArrayList<Object>();
//        map.put("begin_date", begin_date);
        map.put("begin_date", "2016-12-15");
//        map.put("end_date", end_date);
        map.put("end_date", "2016-12-21");
//        String result1 = HttpUtils.doPost(userSummaryUrl1, JSON.toJSONString(map));
//        String result2 = HttpUtils.doPost(userSummaryUrl2, JSON.toJSONString(map));

        String result1 = "{'list': [{'ref_date': '2016-12-05','user_source': 0,'new_user': 1,'cancel_user': 0},"
                + "{'ref_date': '2016-12-06','user_source': 0,'new_user': 2,'cancel_user': 1},"
                + "{'ref_date': '2016-12-07','user_source': 0,'new_user': 1,'cancel_user': 0},"
                + "{'ref_date': '2016-12-08','user_source': 0,'new_user': 2,'cancel_user': 0},"
                + "{'ref_date': '2016-12-09','user_source': 0,'new_user': 1,'cancel_user': 1},"
                + "{'ref_date': '2016-12-10','user_source': 0,'new_user': 1,'cancel_user': 0},"
                + "{'ref_date': '2016-12-11','user_source': 0,'new_user': 3,'cancel_user': 0}]}";
        String result2 = "{'list': [{'ref_date': '2016-12-05','user_source': 0,'cumulate_user': 1}" +
                ",{'ref_date': '2016-12-06','user_source': 0,'cumulate_user': 2}" +
                ",{'ref_date': '2016-12-07','user_source': 0,'cumulate_user': 3}" +
                ",{'ref_date': '2016-12-08','user_source': 0,'cumulate_user': 5}" +
                ",{'ref_date': '2016-12-09','user_source': 0,'cumulate_user': 5}" +
                ",{'ref_date': '2016-12-10','user_source': 0,'cumulate_user': 6}" +
                ",{'ref_date': '2016-12-11','user_source': 0,'cumulate_user': 9}]}";

        if (JSON.parseObject(result1).getString("errCode") != null || JSON.parseObject(result2).getString("errCode") != null) {
            return null;
        } else {
            List<StatisticsUserDto> statisticsUserDtoList = JSON.parseArray(JSON.parseObject(result1).getString("list"), StatisticsUserDto.class);
            List<CumulateUserDto> cumulateUserDtoList = JSON.parseArray(JSON.parseObject(result2).getString("list"), CumulateUserDto.class);
            for (CumulateUserDto cumulateUserDto : cumulateUserDtoList) {
                StatisticsAllUserDto statisticsAllUserDto = new StatisticsAllUserDto();
                for (StatisticsUserDto sDto : statisticsUserDtoList) {
                    if (sDto.getRef_date().equals(cumulateUserDto.getRef_date())) {
                        statisticsAllUserDto.setRef_date(sDto.getRef_date());
                        statisticsAllUserDto.setNew_user(sDto.getNew_user());
                        statisticsAllUserDto.setCancel_user(sDto.getCancel_user());
                        statisticsAllUserDto.setCumulate_user(cumulateUserDto.getCumulate_user());
                        statisticsAllUserDto.setJz_user(sDto.getNew_user() - sDto.getCancel_user());
                        dtoList.add(statisticsAllUserDto);
                    }
                }
            }
            return dtoList;
        }
    }
}