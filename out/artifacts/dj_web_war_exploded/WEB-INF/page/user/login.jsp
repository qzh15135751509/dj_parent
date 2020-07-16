<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
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
        //判断当前窗口路径与加载路径是否一致。
        if(window.top.document.URL != document.URL){
            //将窗口路径与加载路径同步
            window.top.location = document.URL;
        }
        $.validator.setDefaults({
            submitHandler: function() {
                var index = layer.load(0,{shade:0.3});
                $.post(
                    "<%=request.getContextPath()%>/user/login",
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
                            window.location.href = "<%=request.getContextPath()%>/index/toIndex?token=" + data.data.token;
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
                    },
                    userPwd: {
                        required: "请输入密码",
                        minlength: "密码长度不能小于 3 个字符"
                    },
                }
            });
        });


        function toAdd() {
            layer.open({
                type: 2,
                title: '用户注册',
                shade: 0.8,
                area: ['400px', '70%'],
                content: '<%=request.getContextPath()%>/user/toAdd',
            });
        }

    </script>
</head>
    <style>
        .error{
            color:red;
        }
    </style>
<body>
<form id="fm" style="text-align: center"  bgcolor="#CCDDFF">
    用户名：<input type="text" name="userName" /> <br/>
    密码：<input type="text" name="userPwd" /> <br/>
    <input type="submit" value="登录"  /><br/>
    <input type="button" value="还没注册?点我" onclick="toAdd()" /><br/>
</form>

</body>
</html>
