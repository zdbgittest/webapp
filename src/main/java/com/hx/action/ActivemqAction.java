package com.hx.action;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;

import com.hx.base.BaseAction;
import com.hx.model.SmallTest;
import com.hx.mq.Receiver;
import com.hx.mq.Sender;

import net.sf.json.JSONObject;

/**
 * activemq运用的过程中，如果想发挥它最大的优势，那么无论是生产者还是消费者，最好都用多线程处理，
 * 以为activemq就是为了处理大并发，可以异步处理的需求
 * @author zyq
 *
 * 2016年8月18日
 */
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
			
			SmallTest t = new SmallTest();
			t.setStAge("1234");
			t.setStName("张三");
			
			//先讲对象转换成json对象，再讲json对象转换成string
			JSONObject json = JSONObject.fromObject(t);
			String str = json.toString();
			
			sender.sendInfo("name",str);
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
			JSONObject obj = JSONObject.fromObject(receiver.receiveMessage("name"));
			
			SmallTest t = (SmallTest)JSONObject.toBean(obj,SmallTest.class);
			
			System.out.println("age:"+t.getStAge()+"   name:"+t.getStName());
			System.out.println(t.toString());
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
