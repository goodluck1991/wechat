package com.jgj.wechat.main;

import com.jgj.wechat.course.menu.*;
import com.jgj.wechat.course.pojo.Token;
import com.jgj.wechat.course.util.CommonUtil;
import com.jgj.wechat.course.util.MenuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单管理器类
 * Created by guojun.jiao on 2018/6/6.
 */
public class MenuManager {
    private static Logger logger = LoggerFactory.getLogger(MenuManager.class);

    private static Menu getMenu(){
        ClickButton clickButton0 = new ClickButton();
        clickButton0.setName("开源中国");
        clickButton0.setType("click");
        clickButton0.setKey("oschina");

        ClickButton clickButton1 = new ClickButton();
        clickButton1.setName("ITeye");
        clickButton1.setType("click");
        clickButton1.setKey("iteye");

        ViewButton viewButton0 = new ViewButton();
        viewButton0.setName("CocoaChina");
        viewButton0.setType("view");
        viewButton0.setUrl("http://www.iteye.com");

        ViewButton viewButton1 = new ViewButton();
        viewButton1.setName("天猫");
        viewButton1.setType("view");
        viewButton1.setUrl("https://www.tmall.com/");

        ViewButton viewButton2 = new ViewButton();
        viewButton2.setName("京东");
        viewButton2.setType("view");
        viewButton2.setUrl("http://m.jd.com");

        ViewButton viewButton3 = new ViewButton();
        viewButton3.setName("唯品会");
        viewButton3.setType("view");
        viewButton3.setUrl("http://vipshop.com");

        ViewButton viewButton4 = new ViewButton();
        viewButton4.setName("当当网");
        viewButton4.setType("view");
        viewButton4.setUrl("http://m.dangdang.com");

        ViewButton viewButton5 = new ViewButton();
        viewButton5.setName("苏宁易购");
        viewButton5.setType("view");
        viewButton5.setUrl("http://m.suning.com");

        ViewButton viewButton6 = new ViewButton();
        viewButton6.setName("网易云游戏");
        viewButton6.setType("view");
        viewButton6.setUrl("http://game.163.com/");

        ViewButton viewButton7 = new ViewButton();
        viewButton7.setName("腾讯游戏");
        viewButton7.setType("view");
        viewButton7.setUrl("http://game.qq.com/");

        ComplexButton complexButton0 = new ComplexButton();
        complexButton0.setName("技术交流");
        complexButton0.setSub_button(new Button[]{clickButton0,clickButton1,viewButton0});

        ComplexButton complexButton1 = new ComplexButton();
        complexButton1.setName("购物");
        complexButton1.setSub_button(new Button[]{viewButton1,viewButton2,viewButton3,viewButton4,viewButton5});

        ComplexButton complexButton2 = new ComplexButton();
        complexButton2.setName("网页游戏");
        complexButton2.setSub_button(new Button[]{viewButton6,viewButton7});

        Menu menu = new Menu();
        menu.setButton(new Button[]{complexButton0,complexButton1,complexButton2});

        return menu;
    }

    public static void main(String[] args) {
        String appId = "wx1e8c3053cbcab354";
        String appSecret = "09ea7e3c86bbf2a9c01ecf32ce5ec99a";
        Token token = CommonUtil.getToken(appId,appSecret);

        if(token != null){
            boolean result = MenuUtil.createMenu(getMenu(),token.getAccessToken());
            if(result){
                logger.info("菜单创建成功");
            }else{
                logger.info("菜单创建失败");
            }
        }else{
            logger.info("获取token失败");
        }

    }








}
