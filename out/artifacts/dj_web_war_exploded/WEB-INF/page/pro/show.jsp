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

	$(function() {
		search();
	})
	function search() {
			var index = layer.load(0,{shade:0.3});
			$.post("<%=request.getContextPath() %>/pro/show?token=${token}",
					{"pageNo" : $("#pageNo").val()},
			function(data){
				layer.close(index);
				if(data.code != 200){
					layer.msg(data.msg,{icon:5});
					return;
				}
				var html = "";
				var pageHtml = "";
				for (var i = 0; i <  data.data.list.length; i++) {
					var p = data.data.list[i];
					html+="<tr>";
					html+="<td>"+p.proName+"</td>";
					html+="<td>"+p.userName+"</td>";
					html+="<td>";
					html+="<input type='button' value='修改'onclick='upd("+p.id+")'/><input type='button' value='删除'onclick='del("+p.id+")'/>";
					html+="</td>";
					html+="</tr>";
				}
				$("#tbd").html(html);
				pageHtml += "<input type = 'button' value='上一页' onclick='page(0, "+data.data.pages+")'/>";
				pageHtml +="第"+$("#pageNo").val()+"/"+data.data.pages+"页";
				pageHtml += "<input type = 'button' value='下一页' onclick='page(1, "+data.data.pages+")'/>";
				$("#pageInfo").html(pageHtml);
			});
		
	}
	//分页
	function page(temp,pages){
		var page = $("#pageNo").val();
		if (temp == 0) {
			if (page == 1) {
				layer.msg("已是首页", {icon: 5, time: 2000});
				return;
			}
			$("#pageNo").val(parseInt(page) - 1);
		}
		if (temp == 1) {
			if (parseInt(page) + 1 > pages ) {
				layer.msg("已是尾页", {icon: 5, time: 2000});
				return;
			}
			$("#pageNo").val(parseInt(page) + 1);
		}
		search();
	}

	//增加
	function add(){
		layer.open({
			type: 2,
			title: '新增商品',
			shade: 0.8,
			area: ['400px', '70%'],
			content: '<%=request.getContextPath()%>/pro/toAdd?token=${token}',
		});
	}

	//修改
	function upd(id){
		layer.open({
			type: 2,
			title: '修改商品',
			shade: 0.8,
			area: ['400px', '70%'],
			content: '<%=request.getContextPath()%>/pro/toUpdate?token=${token}&id='+id
		});
	}

	//删除
	function del(id){
		var index = layer.load(0,{shade:0.3});
		$.post(
				"<%=request.getContextPath()%>/pro/updateIsDel?token=${token}",
				{"isDel" : 0 , "id" : id},
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
						window.location.href = "<%=request.getContextPath() %>/pro/toShow?token=${token}";
					});
				}
		);
	}
</script>
</head>
<body style="text-align: center"  bgcolor="#CCDDFF">
<h2>商品展示</h2>
	<!-- 分页按钮 -->
	<input type="hidden" value="1" name ="pageNo" id="pageNo"/>
	<input type="button" value="增加" onclick="add()">

	 <table border="2px" cellpadding="10px" cellspacing="0px" bgcolor="pink" align="center">
		<tr>
			<td>商品名</td>
			<td>添加人</td>
			<td>操作</td>
		</tr>
		<tbody id = "tbd">
		
		</tbody>
	</table>
	<div id = "pageInfo"></div>
</body>
</html>