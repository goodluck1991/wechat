package com.jgj.wechat.course.constants;

/**
 * 静态变量
 * Created by guojun.jiao on 2018/5/9.
 */
public class Constants {
    public static final String WECHAT_PARAM_SIGNATURE = "signature";
    public static final String WECHAT_PARAM_TIMESTAMP = "timestamp";
    public static final String WECHAT_PARAM_NONCE = "nonce";
    public static final String WECHAT_PARAM_ECHOSTR = "echostr";
    public static final String WECHAT_PARAM_FROMUSERNAME = "FromUserName";
    public static final String WECHAT_PARAM_TOUSERNAME = "ToUserName";
    public static final String WECHAT_PARAM_MSGTYPE = "MsgType";
    public static final String WECHAT_PARAM_EVENT = "Event";
    public static final String WECHAT_PARAM_EVENT_KEY = "EventKey";

    //菜单获取
    public static final String WECHAT_MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
    //菜单删除
    public static final String WECHAT_MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";
    //菜单创建
    public static final String WECHAT_MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";



}
