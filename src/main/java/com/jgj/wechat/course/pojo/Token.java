package com.jgj.wechat.course.pojo;

/**
 * 凭证
 * Created by guojun.jiao on 2018/6/5.
 */
public class Token {
    //接口访问凭证
    private String accessToken;
    //凭证有效期,单位:秒
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
