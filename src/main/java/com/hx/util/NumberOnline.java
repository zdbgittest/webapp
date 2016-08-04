package com.hx.util;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


/**
 * 在线人数统计，该模块分为两部分，第一部分是统计当前在线人数，应用的是session监听器，维护一个static的数量
 * 第二部分是在线推送部分，这样可以试试的推送数据，当每少一个用户或者没多过一个用户可以试试推送给浏览器
 * @author zyq
 *
 * 2016年6月15日
 */
@ServerEndpoint(value = "/webSocket/goNumberOnline")
public class NumberOnline extends HttpServlet{
	private static final long serialVersionUID = 1L;
	

    private Session session;



    @OnOpen
    public void start(Session session) {
    	this.session = session;
    	JavaCache.addNumberOnline(this);
        System.out.println("新用户进入--在线人数统计页面");
        
        //专门推送给自己
        sendMessageToOneClient(JavaCache.getActiveSessions()+"",this);
    }


    @OnClose
    public void end() {
    	JavaCache.removeNumberOnline(this);
        System.out.println("老用户退出--在线人数统计页面");
    }


    @OnMessage
    public void incoming(String message) {
    }



    @OnError
    public void onError(Throwable t) throws Throwable {
    }


    //推送给所有人
	public static void sendMessageToAllCLient(String msg) {
        for (NumberOnline client : JavaCache.getConnections()) {
            try {
            	client.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
            	JavaCache.removeNumberOnline(client);
            }
        }
    }
	
	//推送给一个人
	public static void sendMessageToOneClient(String msg,NumberOnline no){
		try {
			no.session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
