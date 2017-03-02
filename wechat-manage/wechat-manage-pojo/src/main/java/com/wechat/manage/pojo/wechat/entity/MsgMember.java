package com.wechat.manage.pojo.wechat.entity;

public class MsgMember {
    private Long sid;

    private Long msgSid;

    private Long memberSid;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getMsgSid() {
        return msgSid;
    }

    public void setMsgSid(Long msgSid) {
        this.msgSid = msgSid;
    }

    public Long getMemberSid() {
        return memberSid;
    }

    public void setMemberSid(Long memberSid) {
        this.memberSid = memberSid;
    }
}