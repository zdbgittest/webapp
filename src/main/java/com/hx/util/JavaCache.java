package com.hx.util;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

//存放静态变量
public class JavaCache {
	public static int activeSessions = 0;//目前在线人数是多少
	//浏览器已经和服务器创建的连接的总数
	private static Set<NumberOnline> connections = new CopyOnWriteArraySet<NumberOnline>();
	//开放出一个方法，用来往connections中添加NumberOnline对象
	public static void addNumberOnline(NumberOnline no){
		connections.add(no);
	}
	public static void removeNumberOnline(NumberOnline no){
		connections.remove(no);
	}
	
	
	
	
	
	
	
	public static int getActiveSessions() {
		return activeSessions;
	}
	public static void setActiveSessions(int activeSessions) {
		JavaCache.activeSessions = activeSessions;
	}
	public static Set<NumberOnline> getConnections() {
		return connections;
	}
	public static void setConnections(Set<NumberOnline> connections) {
		JavaCache.connections = connections;
	}
}
