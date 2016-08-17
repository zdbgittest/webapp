package com.hx.action;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;

import com.hx.base.BaseAction;
import com.hx.mq.Receiver;
import com.hx.mq.Sender;

@Controller
@Scope("prototype")
public class ActivemqAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private Receiver receiver;
	private Sender sender;
	private JmsTemplate jmsTemplate;
	
	//前往activemq的测试页面
	public String operList(){
		return SUCCESS;
	}
	
	
	//往消息队列中添加消息
	@SuppressWarnings("resource")
	public void putInQuene(){
		try{
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	        Sender sender = (Sender) context.getBean("sender");
			sender.sendInfo();
			writeAjaxString("成功了");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//从消息队列中得到消息
	@SuppressWarnings("resource")
	public void getOutQuene(){
		try{
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			Receiver receiver = (Receiver) context.getBean("receiver");
			System.out.print(receiver.receiveMessage());
			writeAjaxString("成功了");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	
	
	
	

	public Receiver getReceiver() {
		return receiver;
	}
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
	public Sender getSender() {
		return sender;
	}
	public void setSender(Sender sender) {
		this.sender = sender;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
