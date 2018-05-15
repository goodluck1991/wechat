package com.jgj.wechat.course.message.resp;

import com.jgj.wechat.course.message.resp.model.Video;

/**
 * 视频消息
 * Created by guojun.jiao on 2018/5/9.
 */
public class VideoMessage extends BaseMessage {
//    视频
    private Video video;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
