package com.hx.action;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
		try{
			List<String> list = MemcachedUtils.getAllKeys();
			int totalNum = 0;
			int rightNum = 0;
			int wrongNum = 0;
			
			
			int[] arr = new int[list.size()];
			for(int i=0;i<list.size();i++){
				String a = list.get(i);
				Integer aa = Integer.valueOf(a);
				int aaa = aa.intValue();
				arr[i] = aaa;
			}
			
			Arrays.sort(arr);
			
			for(int i:arr){
				System.out.println(i);
			}
			System.out.println(list.toString());
			
			for(String key:list){
				if(MemcachedUtils.get(key) == null){
					wrongNum++;
				}else{
//				System.out.println("key:"+key+"    value:"+MemcachedUtils.get(key));
					rightNum++;
				}
				totalNum++;
			}
			
			System.out.println("正确："+rightNum);
			System.out.println("错误："+wrongNum);
			System.out.println("总数："+totalNum);
			
			writeAjaxString("获取所有key，已完毕");
		}catch(Exception e){
			e.printStackTrace();
		}
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
