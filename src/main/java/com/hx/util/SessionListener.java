package com.hx.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	
	public void sessionCreated(HttpSessionEvent se) {
        JavaCache.activeSessions++;
        
        NumberOnline.sendMessageToAllCLient(""+JavaCache.activeSessions);
        
        System.out.println("新增一个在线用户："+JavaCache.activeSessions);
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		JavaCache.activeSessions--;
		
		NumberOnline.sendMessageToAllCLient(""+JavaCache.activeSessions);
		
	    System.out.println("减少一个在线用户："+JavaCache.activeSessions);
	}

	
	
	
}
