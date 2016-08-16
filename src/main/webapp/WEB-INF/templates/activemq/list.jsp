<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>activemq测试页面</title>
<script type="text/javascript" src="/jquery/jquery-1.3.1.min.js"></script>
</head>
<body>
	<input type="button" value="往消息队列中添加信息" onclick="putInQuene()">
	<br>
	<input type="button" value="从消息队列中获取信息" onclick="getOutQuene()"/>
</body>
<script type="text/javascript">
	function putInQuene(){
		$.ajax({
			type:"POST",
			url:"/activemq/putInQuene.action",
			dataType:"txt",
			cache:false,
			async:false,
			success:function(data){
				alert(data);
			}
		});
	}
	
	function getOutQuene(){
		$.ajax({
			type:"POST",
			url:"/activemq/getOutQuene.action",
			dataType:"txt",
			cache:false,
			async:false,
			success:function(data){
				alert(data);
			}
		});
	}
</script>
</html>