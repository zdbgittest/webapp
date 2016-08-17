package com.hx.action;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;

import com.hx.base.BaseAction;
import com.hx.model.Test;
import com.hx.mq.Receiver;
import com.hx.mq.Sender;

@Controller
@Scope("prototype")
public class ActivemqAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	@Autowired
	private Receiver receiver;
	@Autowired
	private Sender sender;
	private JmsTemplate jmsTemplate;
	
	//前往activemq的测试页面
	public String operList(){
		return SUCCESS;
	}
	
	
	//往消息队列中添加消息
	public void putInQuene(){
		try{
//			jmsTemplate.send(new MessageCreator() {
//	            public Message createMessage(Session session) throws JMSException {
//	                MapMessage message = session.createMapMessage();
//	                message.setString("lastName", "ppp");
//	                return message;
//	            }
//
//	        });
//			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//	        Sender sender = (Sender) context.getBean("sender");
			
			Test t = new Test();
			t.setAddress("1");
			t.setAge("2");
			t.setName("3");
			t.setRemark("4");
			//先讲对象转换成json对象，再讲json对象转换成string
			
			sender.sendInfo("name",t);
			writeAjaxString("成功了");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//从消息队列中得到消息
	public void getOutQuene(){
		try{
//			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//			Receiver receiver = (Receiver) context.getBean("receiver");
			System.out.print(receiver.receiveMessage("name"));
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
