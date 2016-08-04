package com.hx.test;

import java.util.Date;

import com.hx.util.MemcachedUtils;

public   class  Test1 {  
  
//    public   static   void  main(String args[]) {  
//        Model2 model = new  Model2();  
//        model.setName("hello" );  
//        model.setEmail("testHibernateValidate@sohu.com" );  
//        model.setAddress("湖南长沙" );  
//        model.setPhoneNumber("1501245678" );  
//        model.setAge(2);
//  
//        String result = Util.validateModel(model);// 返回的验证结果，验证结果就是一个字符串，如果有错误的话则该字符串的长度大于0   
//        System.out.println(result); 
//  
//    }  
	public static void main(String[] args) {
		MemcachedUtils.set("haha", "123abc",new Date(1000 * 60));
		Object o = MemcachedUtils.get("haha");
		System.out.println(o.toString());
	}
  
}  