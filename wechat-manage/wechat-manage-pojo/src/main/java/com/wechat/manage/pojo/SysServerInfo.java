package com.wechat.manage.pojo;

import java.util.Date;

public class SysServerInfo {
    private Integer id;

    private String cpuusage;

    private String setcpuusage;

    private String jvmusage;

    private String setjvmusage;

    private String ramusage;

    private String setramusage;

    private String email;

    private Date opertime;

    private String mark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpuusage() {
        return cpuusage;
    }

    public void setCpuusage(String cpuusage) {
        this.cpuusage = cpuusage;
    }

    public String getSetcpuusage() {
        return setcpuusage;
    }

    public void setSetcpuusage(String setcpuusage) {
        this.setcpuusage = setcpuusage;
    }

    public String getJvmusage() {
        return jvmusage;
    }

    public void setJvmusage(String jvmusage) {
        this.jvmusage = jvmusage;
    }

    public String getSetjvmusage() {
        return setjvmusage;
    }

    public void setSetjvmusage(String setjvmusage) {
        this.setjvmusage = setjvmusage;
    }

    public String getRamusage() {
        return ramusage;
    }

    public void setRamusage(String ramusage) {
        this.ramusage = ramusage;
    }

    public String getSetramusage() {
        return setramusage;
    }

    public void setSetramusage(String setramusage) {
        this.setramusage = setramusage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getOpertime() {
        return opertime;
    }

    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}