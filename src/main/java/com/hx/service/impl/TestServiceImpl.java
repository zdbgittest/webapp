package com.hx.service.impl;

import org.springframework.stereotype.Service;

import com.hx.base.BaseService;
import com.hx.service.TestService;
@Service("testService")
public class TestServiceImpl extends BaseService implements TestService{
	private String aaa;
	
	public String sayHollen() {
		System.out.println("hollen");
		return null;
	}

	public String saySimgle(){
		System.out.println("service层中属性aaa:"+aaa);
		return null;
	}
	
	
	public String getAaa() {
		return aaa;
	}
	public void setAaa(String aaa) {
		this.aaa = aaa;
	}

}
