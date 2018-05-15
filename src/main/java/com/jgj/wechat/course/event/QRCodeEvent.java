package com.jgj.wechat.course.event;

/**
 * 扫描带参数二维码事件
 * Created by guojun.jiao on 2018/5/9.
 */
public class QRCodeEvent extends BaseEvent {
//    事件KEY值
    private String Eventkey;
//    用户换取二维码图片
    private String Ticker;

    public String getEventkey() {
        return Eventkey;
    }

    public void setEventkey(String eventkey) {
        Eventkey = eventkey;
    }

    public String getTicker() {
        return Ticker;
    }

    public void setTicker(String ticker) {
        Ticker = ticker;
    }
}
