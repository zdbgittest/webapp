<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/dwr/engine.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/dwr/util.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/dwr/interface/DwrTest.js" type="text/javascript"></script>
<script type="text/javascript">
	DwrTest.testUser(2,"xiejx618",function(data){
		console.log(data);
	});
	function testClick(){
		alert(dwr.util.getValue("xxx"));
	}

</script>
</head>
<body>
<input type="text" id="xxx"/>
<input type="button" value="text" onclick="testClick()"/>
</body>
</html>