package com.jgj.wechat.course.menu;

/**
 * view类型的按钮
 * Created by guojun.jiao on 2018/5/17.
 */
public class ViewButton extends Button{
    private String type;
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
