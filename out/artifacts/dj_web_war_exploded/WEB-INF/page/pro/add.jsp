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
				"<%=request.getContextPath()%>/pro/add?token=${token}",
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
							parent.location.href = "<%=request.getContextPath() %>/pro/toShow?token=${token}";
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
				 proName: {
			        required: true, //required: true 值是必须的
			        minlength: 1, //minlength设置最小长度
			        remote: {
			            url: "<%=request.getContextPath() %>/pro/findProByName?token=${token}",     //后台处理程序
			            type: "post",               //数据发送方式
			            dataType: "json",           //接受数据格式   
			            data: {                     //要传递的数据
							proName: function() {
			                    return $("#proName").val();
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
			    },
			    //messages 处，如果某个控件没有 message，将调用默认的信息
			    messages: {
			      proName: {
			        required: "请输入商品名",
			        minlength: "商品名必需由一个字符组成",
			        remote: "该商品已存在"
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
<body style="text-align: center"  bgcolor="#CCDDFF">
	<form id="fm">
		商品名<input type="text" id="proName" name="proName"/><br/>
		<input type="submit" value="新增" />
	</form>
	
</body>
</html>