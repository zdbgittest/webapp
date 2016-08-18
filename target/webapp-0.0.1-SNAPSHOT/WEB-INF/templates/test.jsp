<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/test/toTestOutSpringToBean.action">测试没有spring工厂的对象属性传递</a>
	<br>
	<a href="/test/toTestStrutsAddSpring.action">测试spring+struts的对象属性参数传递</a>
	<br>
	<a href="/test/testServiceMethod.action">测试action调用service层方法，不使用spring</a>
	<br>
	<a href="/test/testSpringIocService.action">测试spring往action中注入service</a>
	<br>
	<a href="/test/testSimgle.action">测试service层是不是单例的</a>
	<br>
	<a href="/test/testRequestMapping.action">测试RequestMapping是否起作用</a>
	<br>
	<a href="/test/testWobSocket.action">测试websocket 的聊天系统</a>
	<br>
	<a href="/test/toNumberOnline.action">在线人数统计页面</a>
	<br>
	<a href="/test/toLogoutJsp.action">前往在线用户注销页面</a>
	
	<br>
	<a href="/test/testMyTag.action">前往java数据验证页面</a>
</body>
</html>