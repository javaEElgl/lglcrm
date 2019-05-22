<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>新建记录</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
<script type="text/javascript">
function check(){
	if(ck_null('c1','info1')&&ck_null('c2','info2')&&ck_null('c3','info3')){
		dosubmit('form1');
	}
}
</script>
</head>
<body>

<div class="page_title">客户信息管理 &gt;客户信息 &gt;交往记录&gt;新建记录</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
	<button class="common_button" onclick="check();">保存</button>  
</div>
<form action="activity_add" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>时间</th>
			<td><input name="a.date" value="" onblur="ck_null('c1','info1')" id="c1"/><span id="info1" class="red_star">*</span></td>
			<th>地点</th>
			<td><input name="a.place" value="" onblur="ck_null('c2','info2')" id="c2"/><span id="info2" class="red_star">*</span></td>
		</tr>
		<tr>
			<th>概要</th>
			<td><input name="a.title" value="" onblur="ck_null('c3','info3')" id="c3"/><span id="info3" class="red_star">*</span></td>
			<th></th>
			<td></td>
		</tr>	
		<tr>
			<th>详细信息</th>
			<td colspan="3"><textarea name="a.desc" cols="50" rows="6"></textarea></td>
		</tr>
	</table>
</form>
</body>
</html>