package com.hx.action;


import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hx.base.BaseAction;
import com.hx.util.MemcachedUtils;

@Controller
@Scope("prototype")
public class MemcachedAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private String key;
	private String value;
	private int limit;

	public String list(){
//		MemcachedUtils.set("haha", "123abc",new Date(1000 * 60));
//		Object o = MemcachedUtils.get("haha");
//		System.out.println(o.toString());
		return SUCCESS;
	}
	
	
	//前往ajax测试页面
	public String memTest(){
		return SUCCESS;
	}

	
	//ajax保存数据
	public void ajaxSaveMem(){
		MemcachedUtils.set(key, value);
		writeAjaxString("1");
	}
	
	//ajax获取数据
	public void ajaxGetMem(){
		Object o = MemcachedUtils.get(key);
		if(o == null){
			writeAjaxString("");
		}else{
			writeAjaxString(o.toString());
		}
	}
	
	//生成指定个数的memcached值
	public void createLimit(){
		for(int i=0;i<limit;i++){
			key = i+"";
			value = UUID.randomUUID()+"";
			MemcachedUtils.set(key,value);
			System.out.println("key:"+key+"     value:"+value);
		}
//		MemcachedUtils.flashAll();
		writeAjaxString("写完 了");
	}
	
	
	//清空缓存数据
	public void flash(){
		MemcachedUtils.flashAll();
		writeAjaxString("清楚完毕");
	}
	
	
	//获取memcached中所有的key
	public void getAllKey(){
		
	}
	
	
	
	
	

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
