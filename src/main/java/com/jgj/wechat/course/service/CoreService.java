package com.jgj.wechat.course.service;

import com.jgj.wechat.course.constants.Constants;
import com.jgj.wechat.course.message.resp.TextMessage;
import com.jgj.wechat.course.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 核心服务类
 * Created by guojun.jiao on 2018/5/9.
 */
public class CoreService {

    /**
     * 处理微信发来的请求
     * @param request
     * @return
     */
    public static String processRequest(HttpServletRequest request){
//        XML格式的消息数据
        String respXml = null;
//        默认返回的文本消息内容
        String respContent = "未知的消息类型";

        try {
//            调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseXml(request);
//            发送方账号
            String fromUserName = requestMap.get(Constants.WECHAT_PARAM_FROMUSERNAME);
//            开发者账号
            String toUserName = requestMap.get(Constants.WECHAT_PARAM_TOUSERNAME);
//            消息类型
            String msgType = requestMap.get(Constants.WECHAT_PARAM_MSGTYPE);

//            回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

            if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){//文本消息
                respContent = "您发送的是文本信息!";
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){//图片消息
                respContent = "您发送的是图片信息!";
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){//语音消息
                respContent = "您发送的是语音信息!";
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)){//视频消息
                respContent = "您发送的是视频信息!";
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){//地理位置消息
                respContent = "您发送的是地理位置信息!";
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){//链接消息
                respContent = "您发送的是链接信息!";
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){//事件推送
//                事件类型
                String eventType = requestMap.get(Constants.WECHAT_PARAM_EVENT);

                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){//订阅事件
                    respContent = "谢谢您的关注!";
                }else if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){//取消订阅事件
//                    取消订阅后,用户不会再收到公众号账号发送的信息,因此不需要回复
                }else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){//扫描带参数二维码
//                    处理带参数二维码
                }else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)){//上报地理位置
//                    处理上报地理位置事件
                }else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){//自定义菜单
//                    处理菜单点击事件
//                    时间key值判断用户点击的按钮
                    String eventKey =requestMap.get(Constants.WECHAT_PARAM_EVENT_KEY);
                    if(eventKey.equals("V1001_TODAY_MUSIC")){
                        respContent = "用户点击了\"今日歌曲\"按钮!";
                    } else if(eventKey.equals("V1001_HELLO_WORD")){
                        respContent = "用户点击了\"hello word\"按钮!";
                    }else if(eventKey.equals("V1001_GOOD")){
                        respContent = "用户点击了\"赞我们一下\"按钮!";
                    }

                }
            }
//            设置文本消息的内容
            textMessage.setContent(respContent);
//            将文本消息对象装换成XML
            respXml = MessageUtil.messageToXml(textMessage);
        } catch (Exception e){
            e.printStackTrace();
        }
        return respXml;
    }
}
