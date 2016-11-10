package com.hx.service.impl;

import org.springframework.stereotype.Component;

/**
 * 业务类
 * 
 * @author yanbin
 * 
 */
@Component
public class Business {

    /**
     * 切入点
     */
    public String delete(String obj) {
        System.out.println("我是執行的方法");
        int i=1;
        int j=1;
        int g = 10/(i-j);
        return obj + "：瞄～";
    }

    public String add(String obj) {
        System.out.println("================这个方法不能被切。。。============== \n");
        return obj + "：瞄～ 嘿嘿！";
    }

    public String modify(String obj) {
        System.out.println("=================这个也设置加入切吧====================\n");
        return obj + "：瞄改瞄啊！";
    }

}