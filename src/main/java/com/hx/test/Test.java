package com.hx.test;

import java.util.Date;

import com.hx.model.UserBean;
import com.hx.util.MemcachedUtils;

public class Test {

	public static void main(String[] args) {
		
//		MyMemcachedUtil.put("hello", "nohello", 60);
//		String s = (String) MyMemcachedUtil.get("hello");
//		System.out.println("取出来的数据"+s);
//		
//		for(int i=0;i<100;i++){
//			UserBean u =  new UserBean("mmmm-"+i, "kkkk-"+i);
//			MyMemcachedUtil.put("userbean"+i, u,30);
//			UserBean t = (UserBean) MyMemcachedUtil.get("userbean"+i);
//			System.out.println(t.getUsername()+"《——————》"+t.getPassword());
//		}
//		
//		UserBean t = (UserBean) MyMemcachedUtil.get("userbean56");
//		System.out.println(t.getUsername()+"《——————》"+t.getPassword());
		MemcachedUtils.set("haha", "123abc",new Date(1000 * 60));
		Object o = MemcachedUtils.get("haha");
		
	}

}
