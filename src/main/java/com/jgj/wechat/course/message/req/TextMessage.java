package com.jgj.wechat.course.message.req;

/**
 * 文本消息
 * Created by guojun.jiao on 2018/5/9.
 */
public class TextMessage extends BaseMessage {
//    文本消息
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
