package com.jgj.wechat.course.message.req;

/**
 * 图片信息
 * Created by guojun.jiao on 2018/5/9.
 */
public class ImageMessage extends BaseMessage {
//    图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
