package com.wechat.manage.service.wechat.impl;


import com.wechat.manage.mapper.wechat.MemberPointInfoMapper;
import com.wechat.manage.pojo.wechat.vo.MemberPointInfoReturnDto;
import com.wechat.manage.service.wechat.intf.MemberPointInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-25 0025.
 */
@Service
public class MemberPointInfoServiceImpl implements MemberPointInfoService {

    private static Logger logger = LoggerFactory.getLogger(MemberPointInfoServiceImpl.class);

    @Autowired
    private MemberPointInfoMapper memberPointInfoMapper;

    /**
     * 查询积分明细
     *
     * @param paraMap
     * @return
     */
    public List<MemberPointInfoReturnDto> findMemberPointDetailByPara(Map<String, Object> paraMap) {
        logger.info("start com.wfj.service.impl.MemberPointInfoServiceImpl.findMemberPointDetailByPara(),para:" + paraMap.toString());
        String storeCode = paraMap.get("storeCode") + "";
        String memberCode = paraMap.get("memberCode") + "";
        String cardCode = paraMap.get("cardCode") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeCode", storeCode);
        paramMap.put("memberCode", memberCode);
        paramMap.put("cardCode", cardCode);
        List<MemberPointInfoReturnDto> dtoList = memberPointInfoMapper.selectMemberPointDetailListByParam(paramMap);
        for (MemberPointInfoReturnDto tempDto : dtoList) {
            Integer pointType = tempDto.getPointType();
            if (pointType == 1) {
                tempDto.setPointTypeView("消费返增加");
            }

            Date pointTime = tempDto.getPointTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(pointTime);
            tempDto.setPointTimeView(format);
        }
        logger.info("end com.wfj.service.impl.MemberPointInfoServiceImpl.findMemberPointDetailByPara(),return:" + dtoList.toString());
        return dtoList;
    }
}
