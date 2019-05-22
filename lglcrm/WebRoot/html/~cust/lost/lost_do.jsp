<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>确认流失</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">客户流失管理 &gt;确认流失</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
	<button class="common_button" onclick="dosubmit('form1');">确认流失</button>
</div>
<form action="lost_dolost" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>编号</th>
			<td><input name="l.ID" value="${request.editlost.ID}" style="display:none" readonly="readonly"/>${request.editlost.ID}</td>
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
				<textarea  rows="6" cols="50" readonly="readonly">${request.editlost.delay}</textarea></td>
		</tr>
		<tr>
			<th>流失原因</th>
			<td colspan="3">
				<textarea name="l.reason" rows="6" cols="50" ></textarea></td>
		</tr>
	</table>
</form>
<br />
</body>
</html>