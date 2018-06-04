package com.jgj.wechat.course.menu;

/**
 * 复合菜单
 * Created by guojun.jiao on 2018/5/17.
 */
public class ComplexButton extends Button{
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
