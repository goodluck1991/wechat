package com.jgj.wechat.course.menu;

/**
 * 按钮的基类
 * Created by guojun.jiao on 2018/5/17.
 */
public class ClickButton extends Button{
    private String type;
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
