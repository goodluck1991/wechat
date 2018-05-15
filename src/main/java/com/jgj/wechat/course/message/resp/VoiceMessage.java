package com.jgj.wechat.course.message.resp;

import com.jgj.wechat.course.message.resp.model.Voice;

/**
 * 语音消息
 * Created by guojun.jiao on 2018/5/9.
 */
public class VoiceMessage extends BaseMessage {
//    语音
    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}
