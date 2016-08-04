package com.hx.action;




import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hx.base.BaseAction;
import com.hx.model.Test;
import com.hx.service.TestService;
import com.hx.service.impl.TestServiceImpl;

@Controller
@Scope("prototype")
public class TestAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	@Resource(name = "testService")
	private TestService testService;
	

	
	public String test(){
		System.out.println("TestAction 测试");
		return "success";
	}
	
	/**
	 * 下班这两个带TestOutSpringToBean的页面是测试如果没有spring管理对象，是否可以通过表单将对象中的属性传递过来
	 * 如果能够传递过来，那么表名在浏览器和tomcat的交互过程中，进行数据传递的时候主要靠的是struts2，没有利用java的反射
	 * 
	 * @return
	 */
	private String javaToClent;//后天给前台传递的单个属性
	private Test test;
	public String toTestOutSpringToBean(){
		javaToClent = "我是从后台传递到前台的参数";
		//没有spring不能像下边这样直接传递参数，会空指针，需要先创建对象，在设置属性
		//test.setName("我是从后台传递到此前台的对象的属性");
		return SUCCESS;
	}
	
	public String testOutSpringToBean(){
		System.out.println(test.getName());
		System.out.println(test.getAge());
		System.out.println(test.getSex());
		System.out.println(test.getAddress());
		System.out.println(test.getRemark());
		System.out.println(javaToClent);
		return SUCCESS;
	}
	
	
	/**
	 * 下边代码是测试spring是否启动
	 * @return
	 */
//	@SuppressWarnings("resource")
//	public static void main(String[] args) {
//		//查询到配置文件，并赋给ac这个ApplicationContext应用上下文环境对象
//		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
//        Test t = ac.getBean("test",Test.class);//获得配置文件中的id为dog这个bean对象
//        t.setName("张三");
//        System.out.println(t.getName());
//	}
	
	/**
	 * 下边是测试spring整合struts2之后进行的页面对象级别参数的传递
	 * 包括后台往页面传递参数（需要spring+struts的get和set方法支持），还暴扣页面往后台传递参数(只需要struts的get和set方法支持)
	 * 上边的理解完全错误，参数传递过程中根本没用到spring
	 * @return
	 */
	public String toTestStrutsAddSpring(){
		javaToClent = "我是从后台传递到前台的参数";
		//没有spring不能像下边这样直接传递参数，会空指针，需要先创建对象，在设置属性
		//上班这种理解方式是错的，在对象属性在后台和页面之间进行传递的过程中，根本不牵扯到spring
		System.out.println("test:"+test);
		test = new Test();
		test.setName("我是从后台传递到此前台的对象的属性");
		test.setAddress("地址");
		test.setAge("年龄");
		test.setRemark("备注");
		test.setSex("性别");
		System.out.println("test.st:"+test.getSt());
		return SUCCESS;
	}
	public String testStrutsAddSpring(){
		System.out.println(test.getName());
		System.out.println(test.getAge());
		System.out.println(test.getSex());
		System.out.println(test.getAddress());
		System.out.println(test.getRemark());
		System.out.println(javaToClent);
		System.out.println("test.st:"+test.getSt());
		return SUCCESS;
	}
	
	
	/**
	 * 下边方法测试调用service层中的方法
	 * @return
	 */
	public String testServiceMethod(){
		testService = new TestServiceImpl();
		testService.sayHollen();
		return SUCCESS;
	}
	
	
	/**
	 * 下面测试spring往action中注入service
	 * @return
	 */
	public String testSpringIocService(){
		System.out.print("我是带spring依赖注入的：");
		testService.sayHollen();
		System.out.println(test);
		return SUCCESS;
	}
	
	
	/**
	 * 测试service层是否是单例的
	 * @return
	 */
	public String testSimgle(){
		testService.saySimgle();
		testService.setAaa("心智");
		return SUCCESS;
	}
	
	/**
	 * 测试@RequestMapping(value="/softpg/ajaxLoadSoftId.do",method = POST)
	 * 事实证明不可以，这个标签是springmvc的
	 * @return
	 */
	@RequestMapping(value="/test/testRequestMapping.action")
	public String testRequestMapping(){
		testService.saySimgle();
		System.out.println("直接写地址，看能不能访问到方法");
		return null;
	}
	
	
	
	
	/**
	 * 测试获取web.xml中的context-param参数值
	 * @return
	 */
	public String testComtextParam(){
//		ServletContext sctx = getServletContext();//创建ServletContext对象
//		String 参数值 = sctx.getInitParameter(String 参数名);
		return SUCCESS;
	}
	
	
	/**
	 * 前往推送的测试页面
	 * @return
	 */
	public String testWobSocket(){
		
		return SUCCESS;
	}
	
	/**
	 * 前往当前在线人数页面
	 * @return
	 */
	public String toNumberOnline(){
		return SUCCESS;
	}
	
	
	/**
	 * 前往在线用户注销页面
	 * @return
	 */
	public String toLogoutJsp(){
		return SUCCESS;
	}
	
	
	/**
	 * 我是ajax销毁session方法
	 * @return
	 */
	public void destorySession(){
		getSession().invalidate();
	}
	
	
	/**
	 * 前往自定义标签测试页面
	 * @return
	 */
	public String testMyTag(){
		
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getJavaToClent() {
		return javaToClent;
	}

	public void setJavaToClent(String javaToClent) {
		this.javaToClent = javaToClent;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}
	
}
