<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/res/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/res/layer/layer.js"></script>
<script type="text/javascript">
	function upd() {
		var index = layer.load(0,{shade:0.3});
		$.post(
				"<%=request.getContextPath() %>/user/update?token=${token}",
				$("#fm").serialize(),
				function(data){
					layer.close(index);
					if (data.code != 200) {
						layer.msg(data.msg,{icon:5});
						return;
					}
					layer.msg(data.msg, {
						icon: 6,
						time: 2000 //2秒关闭（如果不配置，默认是3秒）
					}, function(){
						parent.location.href = "<%=request.getContextPath() %>/user/toShow?token=${token}";
					});
				}
		);
	}
</script>
</head>
<style>
	.error{
		color:red;
	}
</style>
<body style="text-align: center"  bgcolor="#CCDDFF">
	<form id="fm">
		<input type="hidden" name="id" value="${user.id}"/>
		用户名<input type="text" name="userName" value="${user.userName}"/><br/>
		密码<input type="text" name="userPwd" value="${user.userPwd}" />
		<input type="button" value="修改" onclick="upd()"/>
	</form>
	
</body>
</html>