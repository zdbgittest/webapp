package com.hx.action;


import java.util.Date;
import java.util.List;
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
	
	
	
	/**
	 * 所有 的测试都在这
	 * @return
	 */
	public void test(){
		try {
//			下边是测试一个新的获取所有key的方法
//			Map<String,KeysBean> map = MemcachedUtils.getKeysForMap();
//			int i=0;
//			for (Map.Entry<String, KeysBean> entry : map.entrySet()) {
//				String key = entry.getKey();
//				KeysBean kb = entry.getValue();
//				
//				System.out.println("key:"+key+"     server:"+kb.getServer()
//					+"    byte:"+kb.getBytes()+"     expiry:"+kb.getExpiry());
//				i++;
//			}
//			System.out.println(i);
			
			//编边是测试一个新的获取key的方法2
//			List<String> list = MemcachedUtils.getAllKeys();        
//			int i=0;                                                          
//			for (String s:list) {        
//				System.out.println("key:"+s+"    value:"+MemcachedUtils.get(s));  
//				i++;
//			}                                                                 
//			System.out.println(i);  
			
			
			//设置缓存失效时间
			MemcachedUtils.set("testTime", "测试缓存失效时间", new Date(1000*10));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//下边这个是设置抢单的初始信息，比如抢什么，总共多少个，抢单有效时间
	public void setRobOrder(){
		
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
