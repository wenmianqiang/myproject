package com.demo.utils;

import java.sql.Timestamp;

public class RespData {
    private int code = 0;//code=0表示成功，非0表示失败
    private String msg = null; // 返回提示语 例如：登录失败
    private Timestamp date ;//返回具体时间
    private long time;//返回时间戳
    private Object data; // 请求返回的实体

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
