<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.1.min.js"></script>
	<script type="text/javascript">   
	    window.onbeforeunload = onbeforeunload_handler;   
	    window.onunload = onunload_handler;   
	    function onbeforeunload_handler(){   
	    	$.post("/test/destorySession.action",{},function(result){});		   
	    }   
	       
	    function onunload_handler(){   
	    	$.post("/test/destorySession.action",{},function(result){});		   
	    }   
	</script> 
</head>
<body >
	本页面关闭，或者跳转之后都将session销毁，用来检测实时在线用户
	<br>
	<a href="/test/test.action">点我返回操作列表</a>
</body>
</html>