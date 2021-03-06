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
<script type="text/javascript" src="<%=request.getContextPath() %>/res/jquery-validation-1.14.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/res/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript">
	$.validator.setDefaults({
	    submitHandler: function() {
	    	var index = layer.load(0,{shade:0.3});
			$.post(
				"<%=request.getContextPath()%>/user/add",
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
							parent.location.href = "<%=request.getContextPath() %>/user/toLogin";
						});
				}
			);	
	    }
	});

	$().ready(function() {
		//在键盘按下并释放及提交后验证提交表单
		 $("#fm").validate({
			 rules: {
				 //此处name需要和input的name值对应
				 //name：名称："校验规则"
			      userName: {
			        required: true, //required: true 值是必须的
			        minlength: 1, //minlength设置最小长度
			        remote: {
			            url: "<%=request.getContextPath() %>/user/findUserByName",     //后台处理程序
			            type: "post",               //数据发送方式
			            dataType: "json",           //接受数据格式   
			            data: {                     //要传递的数据
			                userName: function() {
			                    return $("#userName").val();
			                }
			            }, 
			            dataFilter(data,type) {
			            	if (data == 'true') {
			            		return true;
			            	} else {
			            		return false;
			            	}
			            }
			        },
			      }, 
			      userPwd: {
			        required: true,
			        minlength: 3
			      },
			    },
			    //messages 处，如果某个控件没有 message，将调用默认的信息
			    messages: {
			      userName: {
			        required: "请输入用户名",
			        minlength: "用户名必需由一个字符组成",
			        remote: "该用户已存在"
			      },
			      userPwd: {
			        required: "请输入密码",
			        minlength: "密码长度不能小于3个字符"
			      },
			     }
			});   
		});
		
		
	
</script>
</head>
<style>
	.error{
		color:red;
	}
</style>
<body>
	<form id="fm">
		用户名<input type="text" id="userName" name="userName"/><br/>
		密码<input type="text" name="userPwd"/><br/>
		<input type="submit" value="注册" />
	</form>
	
</body>
</html>