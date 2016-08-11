<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/jquery/jquery-1.3.1.min.js"></script>
<title>第二个memcached测试页面</title>
</head>
<body>
	<br><br>
	下边是往memcached中添加数据
	<br>
	key：<input id="key" type="text"/>
	value：<input id="value" type="text"/>
	<input type="button" value="提交" onclick="setMem()"/>
	<span style="color: red;width: 200px;" id="notice"></span>
	缓存消失提醒：<span style="color: red;width: 200px;" id="timeSpanId"></span>
	
	
	
	<br><br>
	下边是获取memcached中的值
	<br>
	key：<input type="text" id="getMemKey" />
	value：<span style="color: red;width: 200px;" id="getValue"></span>
	<input type="button" id="" value="获取值" onclick="getMem()"/>
	
	
	<br><br>
	下边是测试memcached缓存
	<br>
	key：<input type="text" id="testKey"/>
	value：<input type="text" id="testValue"/>
	<input type="button" id="" value="replace" onclick="testMem()"/>
	<span style="color: red;width: 200px;" id="testMemNotice"></span>
</body>
<script type="text/javascript">
	var time;
	function setMem(){
		$.ajax({
			type:"POST",
			url:"/mem/setMem.action",
			data:"key="+$("#key").val()+"&value="+$("#value").val(),
			dataType:"txt",
			cache:false,
			async:false,
			success:function(data){
				$("#notice").text(data);
				$("#testKey").val($("#key").val());
				$("#getMemKey").val($("#key").val());
				
				
				time = 20;
				$("#timeSpanId").text(time);
				setInterval(function(){daojishi();},1000);
			}
		});
	}
	
	function daojishi(){
		if(time == 0){
			$("#timeSpanId").text("缓存消失了！");
		}else{
			time = time - 1;
			$("#timeSpanId").text(time);
		}
	}
	
	
	
	function getMem(){
		$.ajax({
			type:"POST",
			url:"/mem/getMem.action",
			data:"key="+$("#getMemKey").val(),
			dataType:"txt",
			cache:false,
			async:false,
			success:function(data){
				$("#getValue").text(data);
			}
		});
	}
	
	function testMem(){
		$.ajax({
			type:"POST",
			url:"/mem/testMem.action",
			data:"key="+$("#testKey").val()+"&value="+$("#testValue").val(),
			dataType:"txt",
			cache:false,
			async:false,
			success:function(data){
				$("#testMemNotice").text(data);
			}
		});
	}
</script>
</html>