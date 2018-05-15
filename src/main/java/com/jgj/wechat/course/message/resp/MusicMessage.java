package com.jgj.wechat.course.message.resp;

import com.jgj.wechat.course.message.resp.model.Music;

/**
 * 音乐信息
 * Created by guojun.jiao on 2018/5/9.
 */
public class MusicMessage extends BaseMessage{
//    音乐
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
