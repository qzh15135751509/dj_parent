
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/res/js/jquery-1.12.4.js"></script>
<script type="text/javascript">

$(function(){
setInterval("$('#dateTime').html(new Date().toLocaleString()+'星期'+'日一二三四五六'.charAt(new Date().getDay()));",1000);
})
 
</script>
</head>
 <!-- style="text-align:center"  -->
<body style="text-align:center"  >
	<a href = "<%=request.getContextPath() %>/index/out">退出登录</a>
	<marquee><h1 align="center">欢迎${user.userName}登录北京点金教育平台，万元高新，尽在点金!</h1></marquee>
	<div id="dateTime" align="right"></div>
	
</body>
</html>