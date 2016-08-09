<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/jquery/jquery-1.3.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<br><br>
	下边是测试memcached的保存动作：
	<br>
	key:<input type="text" id="saveMemKey" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	value:<input type="text" id="saveMemValue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="保存" onclick="saveMem()">
	<span style="color:red;" id="noticeSpanId"></span>
	
	
	<br><br>
	下边是测试memcached的获取动作：
	<br>
	key:<input type="text" id="getMemVKey"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	value:<span style="color: red;width: 200px;" id="getMemValue"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="获取" onclick="getMem()">
	
	
	<br><br>
	下边是测试memcached的效率
	<br>
	key从1开始到指定的显示数，value是64位id，现在看看查询效率怎么样
	<br>
	limit:<input type="text" id="saveMemLimit"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="生成" onclick="create()">
	
	
	<br><br>
	<input type="button" value="清除数据" onclick="flash()">
	
	<br><br>
	获取memcached中所有的key
	<br>
	<input type="button" value="获取所有的key" onclick="getAllKeys()"/>
	
	
	<br><br>
	控制台输出test
	<br>
	<input type="button" value="测试" onclick="test()">
	
</body>
<script type="text/javascript">
	function saveMem(){
		$.ajax({
			type:"POST",
			url:"/mem/ajaxSaveMem.action",
			data:"key="+$("#saveMemKey").val()+"&value="+$("#saveMemValue").val(),
			dataType:"txt",
			cache:false,
			async:false,
			success:function(data){
				if(data == '1'){
					$("#noticeSpanId").text("成功");
					$("#getMemVKey").val($("#saveMemKey").val());
					setTimeout(function () { $("#noticeSpanId").text("");}, 3000);
				}
			}
			
		});
	}
	
	function getMem(){
		$.ajax({
			type:"POST",
			url:"/mem/ajaxGetMem.action",
			data:"key="+$("#getMemVKey").val(),
			dataType:"txt",
			cache:false,
			async:false,
			success:function(data){
				$("#getMemValue").text(data);
			}
			
		});
	}
	
	
	function create(){
		$.ajax({
			type:"POST",
			url:"/mem/createLimit.action",
			data:"limit="+$("#saveMemLimit").val(),
			dataType:"txt",
			cache:false,
			async:false,
			success:function(data){
				alert(data);
			}
			
		});
	}
	
	function flash(){
		$.ajax({
			type:"POST",
			url:"/mem/flash.action",
			dataType:"txt",
			cache:false,
			async:false,
			success:function(data){
				alert(data);
			}
		});
	}
	
	
	function getAllKeys(){
		$.ajax({
			type:"POST",
			url:"/mem/getAllKey.action",
			dataType:"txt",
			cache:false,
			async:false,
			success:function(data){
				alert(data);
			}
		});
	}
	
	function test(){
		$.ajax({
			type:"POST",
			url:"/mem/test.action",
			dataType:"txt",
			cache:false,
			async:false,
			success:function(data){
			}
		});
	}
</script>
</html>