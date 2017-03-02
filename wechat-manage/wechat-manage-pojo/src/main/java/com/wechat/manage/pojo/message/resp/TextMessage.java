package com.wechat.manage.pojo.message.resp;

/**
 * 文本消息消息体
 * @Class Name TextMessage
 * @Author kongqf
 * @Create In 2016年9月22日
 */
public class TextMessage extends BaseMessage {  
    // 回复的消息内容   
    private String Content;  

    public String getContent() {  
        return Content;  
    }  

    public void setContent(String content) {  
        Content = content;  
    }  
}