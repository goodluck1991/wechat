package com.jgj.wechat.course.message.resp;

/**
 * 请求消息基类(公众账号=>普通用户)
 * Created by guojun.jiao on 2018/5/9.
 */
public class BaseMessage {
    //    开发者微信号
    private String ToUserName;
    //    发送方账号(openid)
    private String FromUserName;
    //    消息创建时间(整型)
    private long CreateTime;
    //    消息类型(text/music/news)
    private String MsgType;
    //    消息ID,64位整型
    private long MsgId;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                ", MsgId=" + MsgId +
                '}';
    }
}