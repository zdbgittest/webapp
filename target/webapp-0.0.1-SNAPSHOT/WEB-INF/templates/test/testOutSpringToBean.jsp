<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/test/testOutSpringToBean.action" id="Form" method="post">
		name:<input type="text" name="test.name" value="${test.name}"/><br/>
		age:<input type="text" name="test.age" value="${test.age}"/><br/>
		sex:<input type="text" name="test.sex" value="${test.sex}"/><br/>
		address:<input type="text" name="test.address" value="${test.address}"/><br/>
		remark:<input type="text" name="test.remark" value="${test.remark}"/><br/>
		非对象属性javaToClent:<input type="text" name="javaToClent" value="${javaToClent}"/><br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>