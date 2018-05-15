package com.jgj.wechat.course.event;

/**
 * 自定义菜单事件
 * Created by guojun.jiao on 2018/5/9.
 */
public class MenuEvent extends BaseEvent {
//    时间KEY值,与自定义菜单接口中KEY值对应
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
