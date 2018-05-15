package com.jgj.wechat.course.message.resp.model;

/**
 * 视频model
 * Created by guojun.jiao on 2018/5/9.
 */
public class Video {
//    媒体文件ID
    private String MediaId;
//    缩略图的媒体ID
    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
