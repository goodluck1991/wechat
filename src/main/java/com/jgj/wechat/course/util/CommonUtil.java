package com.jgj.wechat.course.util;

import com.jgj.wechat.course.constants.Constants;
import com.jgj.wechat.course.pojo.Token;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;

/**
 * 通用工具类
 * Created by guojun.jiao on 2018/6/5.
 */
public class CommonUtil {
    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
    //获取token https请求方式: GET
    public static final String WECHAT_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 发送https请求
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式(GET/POST)
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取JSON对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl,String requestMethod,String outputStr){
        JSONObject jsonObject = null;
        try{
            //创建SSLContext对象,并使用我们制定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
            sslContext.init(null,tm,new SecureRandom());
            //从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection)url.openConnection();
            httpsURLConnection.setSSLSocketFactory(ssf);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setUseCaches(false);
            //设置请求方式(GET/POST)
            httpsURLConnection.setRequestMethod(requestMethod);
            //当outputStr不为null时,向输出流写数据
            if(null!=outputStr){
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            //从输入流读取返回内容
            InputStream inputStream = httpsURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine())!=null){
                buffer.append(str);
            }
            //释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpsURLConnection.disconnect();;
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch(ConnectException ce){
            logger.error("连接超时:{}",ce);
        } catch (Exception e){
            logger.error("https请求异常:{}",e);
        }
        return jsonObject;
    }

    /**
     * 获取接口访问凭证
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static Token getToken(String appid, String appsecret){
        Token token = null;
        String requestUrl = WECHAT_TOKEN_URL.replace("APPID",appid).replace("APPSECRET",appsecret);
        //发起get请求获取凭证
        JSONObject jsonObject = httpsRequest(requestUrl,"GET",null);
        if(null != jsonObject){
            try {
                token = new Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            }catch (JSONException e){
                token = null;
                //获取token失败
                logger.error("获取token失败errcode{},errmsg:{}",
                        jsonObject.getInt("errcode"),
                        jsonObject.getString("errmsg"));
            }
        }
        return token;
    }
}
