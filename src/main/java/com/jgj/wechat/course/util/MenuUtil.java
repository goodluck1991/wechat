package com.jgj.wechat.course.util;

import com.jgj.wechat.course.menu.Menu;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;

/**
 * 自定义菜单工具组
 * Created by guojun.jiao on 2018/6/5.
 */
public class MenuUtil {
    private static Logger logger = LoggerFactory.getLogger(MenuUtil.class);

    //菜单获取 https请求方式: GET
    public static final String WECHAT_MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    //菜单删除 https请求方式: GET
    public static final String WECHAT_MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    //菜单创建 https请求方式: POST
    public static final String WECHAT_MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 创建菜单
     * @param menu 菜单实例
     * @param accessToken 凭证
     * @return true 成功  false 失败
     */
    public static boolean createMenu(Menu menu,String accessToken){
        boolean result = false;
        String requestUrl = WECHAT_MENU_CREATE_URL.replace("ACCESS_TOKEN",accessToken);
        //将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        //发起post请求创建菜单
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl,"POST",jsonMenu);
        if(null != jsonObject){
            int errorCode = jsonObject.getInt("errcode");
            String errmsg = jsonObject.getString("errmsg");
            if(0 == errorCode){
                result = true;
            }else{
                logger.error("创建菜单失败errorCode:{},errmsg:{}",errorCode,errmsg);
            }
        }
        return result;
    }

    /**
     * 查询菜单
     * @param accessToken
     * @return
     */
    public static String getWechatMenuGetUrl(String accessToken){
        String result = null;
        String requestUrl = WECHAT_MENU_GET_URL.replace("ACCESS_TOKEN","accessToken");
        //发起get请求查询菜单
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl,"GET",null);
        if(null != jsonObject){
            result = jsonObject.toString();
        }
        return result;
    }

    /**
     * 删除菜单
     * @param accessToken
     * @return
     */
    public static boolean deleteMenu(String accessToken){
        boolean result = false;
        String requestUrl = WECHAT_MENU_DELETE_URL.replace("ACCESS_TOKEN","accessToken");
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl,"GET",null);
        if(null != jsonObject){
            int errcode = jsonObject.getInt("errcode");
            String errmsg = jsonObject.getString("errmsg");
            if(0 == errcode){
                result = true;
            }else{
                logger.error("删除菜单失败errcode:{},errmsg:{}",errcode,errmsg);
            }
        }
        return result;
    }
}
