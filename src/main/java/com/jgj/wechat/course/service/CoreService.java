package com.jgj.wechat.course.service;

import com.jgj.wechat.course.constants.Constants;
import com.jgj.wechat.course.message.resp.NewsMessage;
import com.jgj.wechat.course.message.resp.TextMessage;
import com.jgj.wechat.course.message.resp.model.Article;
import com.jgj.wechat.course.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 核心服务类
 * Created by guojun.jiao on 2018/5/9.
 */
public class CoreService {
    private static Logger logger = LoggerFactory.getLogger(CoreService.class);
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
            logger.info("微信公众号请求参数:{}",requestMap);
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
//                语音消息文件的标识
//                String mediaId = requestMap.get("MediaId");
////                语音格式:amr
//                String format = requestMap.get("Format");
////                语音识别结果
//                String recognition = requestMap.get("Recognition");

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
                    respContent = "您好,欢迎关注网址导航!我们致力于打造精品网址聚合应用,为用户提供表姐的上网导航服务.体验生活,从这里开始!!";
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
                    if(eventKey.equals("oschina")){
                        Article article = new Article();
                        article.setTitle("开源中国");
                        article.setDescription("开源中国社区成立于2008年8月,是目前中国最大的开源技术社区.\n\n开源中国的目的是为" +
                                "中国的IT技术人员提供一个全面的,快捷更新的,用来检索开源软件以及交流开源经验的平台");
                        article.setPicUrl("");
                        article.setUrl("http://m.oschina.net");
                        List<Article> articleList = new ArrayList<Article>();
                        articleList.add(article);
                        //创建图文消息
                        NewsMessage newsMessage = new NewsMessage();
                        newsMessage.setToUserName(fromUserName);
                        newsMessage.setFromUserName(toUserName);
                        newsMessage.setCreateTime(new Date().getTime());
                        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                        newsMessage.setArticleCount(articleList.size());
                        newsMessage.setArticles(articleList);
                        respXml = MessageUtil.messageToXml(newsMessage).replace("com.jgj.wechat.course.message.resp.model.Article","item");
                    } else if(eventKey.equals("iteye")){
                        textMessage.setContent("ITeye即创办于2003年9月的JavaEye,从最初的以讨论Java技术为主的技术论坛,已经" +
                                "逐渐发展成为涵盖整个软件开发领域的综合性网站.\n\nhttp://www.iteye.com");
                        respXml = MessageUtil.messageToXml(textMessage);
                    }else{
//                        设置文本消息的内容
                        textMessage.setContent(respContent);
//                        将文本消息对象装换成XML
                        respXml = MessageUtil.messageToXml(textMessage);
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return respXml;
    }
}
