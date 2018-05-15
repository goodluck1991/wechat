package com.jgj.wechat.course.message.resp.model;

/**图文model
 * Created by guojun.jiao on 2018/5/9.
 */
public class Article {
//    图文信息名称
    private String Title;
//    图文信息描述
    private String Description;
//    图片链接,支持JPG,PNG格式,较好的效果为大图640像素X320像素,小图80像素X80像素
    private String PicUrl;
//    点击图文消息跳转链接
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
