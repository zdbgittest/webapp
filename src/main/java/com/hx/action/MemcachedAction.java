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
//			MemcachedUtils.set("testTime", "测试缓存失效时间", new Date(1000*10));
			
			System.out.println("商品剩余数量："+MemcachedUtils.get("912456"));
			System.out.println("抢单结束时间："+MemcachedUtils.get("912456endTime"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private String openId;//抢什么的标识，比如商品的id
	private int num;//总共多少个
	private int endTime;//能抢多久
	//下边这个是设置抢单的初始信息，比如抢什么，总共多少个，抢单截止时间
	//由于memcached初始化设置的缓存中有失效时间，那么在替换value时，如果用set，那么失效时间办法简单直观的设置
	//如果用replace，依旧是失效时间无法设置，所以需要的单独存放一个失效时间，每次改变剩余数量的时候都需要进行计算剩余时间然后设置进去
	public void setRobOrder(){
		MemcachedUtils.set(openId, num, new Date(1000*endTime));
		
		
		MemcachedUtils.set(openId+"endTime", endTime);
		
		writeAjaxString("成功了");
	}
	
	private String userId;
	private static int EXPDATE = 3600;//多久过期
	//抢单，其中userid为当前操作人的id，这里直接从页面上传到后台
	//抢单中需要判断当前用户是否已经抢过单子了，那么判断方法也是缓存
	//一旦当前用户成功的抢到了单子，那么把   抢单的标识（如商品id）-userid 的形式放在缓存中，
	//这样每次用户抢单的时候可以去判断是否还能抢单了
	//而且用户抢单成功，需要减少总数量，这个环节需要进行并发处理
	public void robOrder(){
		boolean flag = (Boolean) MemcachedUtils.get(openId+userId);
		System.out.println(flag);
		
		if(!flag){
			MemcachedUtils.set(openId+userId, true, new Date(EXPDATE));//设置当前用户的抢到的订单。且该订单有 有效期
			num = (Integer) MemcachedUtils.get(openId);//得到剩余的能抢的商品的数量
			num = num - 1;//减一
			//再把抢过之后的商品数量和抢单结束日期放入缓存中
			MemcachedUtils.set(openId, num,(Date)MemcachedUtils.get(openId+"endTime"));
		}
	}
	
	
	
	
	
	
	
	
	
	
	//新的测试页面
	public String memTestNew(){
		return SUCCESS;
	}
	
	//设置值
	public void setMem(){
		MemcachedUtils.set(key, value,new Date(1000*20));
		writeAjaxString("成功了");
	}
	
	//获取值
	public void getMem(){
		Object o = MemcachedUtils.get(key);
		if(o!=null){
			writeAjaxString(o.toString());
		}else{
			writeAjaxString("");
		}
	}
	
	//各种测试
	public void testMem(){
		MemcachedUtils.testMem(key, value);
		
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public static void main(String[] args) {
		MemcachedUtils.set("err", "err", new Date(1000*20));
	}
	public static int getEXPDATE() {
		return EXPDATE;
	}
	public static void setEXPDATE(int eXPDATE) {
		EXPDATE = eXPDATE;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
}
