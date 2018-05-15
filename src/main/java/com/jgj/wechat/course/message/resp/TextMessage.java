package com.jgj.wechat.course.message.resp;

/**
 * 文本消息
 * Created by guojun.jiao on 2018/5/9.
 */
public class TextMessage extends BaseMessage{
//    回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
