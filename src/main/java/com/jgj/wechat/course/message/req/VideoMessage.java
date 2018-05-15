package com.jgj.wechat.course.message.req;

/**
 * 视频信息
 * Created by guojun.jiao on 2018/5/9.
 */
public class VideoMessage extends BaseMessage {
//    视频消息媒体ID
    private String MediaId;
//    视频消息缩略图的媒体ID
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
