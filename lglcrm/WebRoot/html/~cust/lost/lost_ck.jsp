<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>查看流失</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">客户流失管理 &gt;流失详情</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
</div>
	<table class="query_form_table">
		<tr>
			<th>编号</th>
			<td>${request.editlost.ID}</td>
			<th>客户</th>
			<td>${request.editlost.customer}</td>
		</tr>
		<tr>
			<th>客户经理</th>
			<td>${request.editlost.customer}"</td>
			<th>上次下单时间</th>
			<td>${request.editlost.customer}"</td>
		</tr>	
		<tr>
			<th>暂缓措施</th>
			<td colspan="3">
				<textarea  name="l.delay" rows="6" cols="50" readonly="readonly">${request.editlost.delay}</textarea></td>
		</tr>
		<tr>
			<th>流失原因</th>
			<td colspan="3">
				<textarea  name="l.delay" rows="6" cols="50" readonly="readonly">${request.editlost.reason}</textarea></td>
		</tr>
	</table>
<br />
</body>
</html>