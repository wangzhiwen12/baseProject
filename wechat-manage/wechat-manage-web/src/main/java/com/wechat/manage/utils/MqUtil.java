package com.wechat.manage.utils;


import com.wechat.manage.vo.MqResponse;
import com.wechat.manage.vo.RequestHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MqUtil {

    private static final Logger logger = LoggerFactory.getLogger(MqUtil.class);

    /**
     * 接收到MQ信息后返回信息
     *
     * @param header
     * @return MqResponse
     * @Methods Name GetMqResponseInfo
     * @Create In 2015年9月10日 By kongqf
     */
    public static MqResponse GetMqResponseInfo(RequestHeader header) {
        MqResponse response = new MqResponse();
        response.setMessageID(header.getMessageID());
        response.setServiceID(header.getServiceID());
        response.setRespStatus("1");
        return response;
    }

}
