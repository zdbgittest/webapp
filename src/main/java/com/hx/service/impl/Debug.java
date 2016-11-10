package com.hx.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Debug {
	  public static void main(String[] args) {
	        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	        Business business = (Business) context.getBean("business");
	        business.delete("猫");
	    }
}
