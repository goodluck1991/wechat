package com.jgj.wechat.course.message.resp;

import com.jgj.wechat.course.message.resp.model.Image;

/**
 * 图片消息
 * Created by guojun.jiao on 2018/5/9.
 */
public class ImageMessage extends BaseMessage {
//    图片
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
