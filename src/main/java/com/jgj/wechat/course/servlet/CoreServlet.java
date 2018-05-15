package com.jgj.wechat.course.servlet;

import com.jgj.wechat.course.constants.Constants;
import com.jgj.wechat.course.service.CoreService;
import com.jgj.wechat.course.util.SignUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by guojun.jiao on 2018/5/7.
 */
@WebServlet(name = "CoreServlet",urlPatterns = "/CoreServlet")
public class CoreServlet extends HttpServlet {
//    private static final Logger LOGGER = Logger.getLogger(CoreServlet.class);

    /**
     * 请求校验(确认请求来自微信服务器)
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        微信加密签名
        String signature = request.getParameter(Constants.WECHAT_PARAM_SIGNATURE);
//        时间戳
        String timestamp = request.getParameter(Constants.WECHAT_PARAM_TIMESTAMP);
//        随机数
        String nonce = request.getParameter(Constants.WECHAT_PARAM_NONCE);
//        随机字符串
        String echostr = request.getParameter(Constants.WECHAT_PARAM_ECHOSTR);

        PrintWriter out = response.getWriter();
//        请求校验,若校验成功返回原来的echostr,表示接入成功,否则接入失败
        if(SignUtil.checkSignature(signature,timestamp,nonce)){
            out.print(echostr);
        }
        out.close();
        out = null;
    }


    /**
     * 处理微信服务器发来的消息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        LOGGER.debug("微信公众号被请求");
//        将请求,响应的编码均设置成UTF-8(防止中文乱码)
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        接受参数:微信加密签名,时间戳,随机数,随机字符串
        String signature = request.getParameter(Constants.WECHAT_PARAM_SIGNATURE);
        String timestamp = request.getParameter(Constants.WECHAT_PARAM_TIMESTAMP);
        String nonce = request.getParameter(Constants.WECHAT_PARAM_NONCE);
        String echostr = request.getParameter(Constants.WECHAT_PARAM_ECHOSTR);

        PrintWriter out = response.getWriter();
//        请求校验,若校验成功返回原来的echostr,表示接入成功,否则接入失败
        if(SignUtil.checkSignature(signature,timestamp,nonce)){
//            调用核心服务类接受处理请求
            String respXml = CoreService.processRequest(request);
            out.print(respXml);
        }
        out.close();
        out = null;
    }
}
