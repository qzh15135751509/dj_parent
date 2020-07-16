<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- <a href="userShow.jsp" target="right">用户列表展示</a><p> -->
	<a href="<%=request.getContextPath()%>/pro/toShow?token=${token}" target="right">商品展示</a><p>
	<a href="<%=request.getContextPath()%>/user/toShow?token=${token}" target="right">用户展示</a><p>
</body>
</html>