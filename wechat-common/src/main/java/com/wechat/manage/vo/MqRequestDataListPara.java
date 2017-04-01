package com.wechat.manage.vo;

import java.util.List;

/**
 * Created by kongqf on 2017/3/30.
 */
public class MqRequestDataListPara<T> {

    public List<T> data;
    public RequestHeader header;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public RequestHeader getHeader() {
        return header;
    }

    public void setHeader(RequestHeader header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "MqRequestDataListPara [data=" + data + ", header=" + header + "]";
    }

}