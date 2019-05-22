<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>交往记录</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">客户信息管理 &gt;客户信息 &gt;交往记录 </div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>  
	<button class="common_button" onclick="to('activity_toadd');">添加记录</button>  
</div>
<table class="query_form_table">
	<tr>
		<th>客户编号</th>
		<td>${session.editcustomer.no}</td>
		<th>客户名称</th>
		<td>${session.editcustomer.name}</td>
	</tr>
</table>
<br />
<table class="data_list_table">
	<tr>
		<th>时间</th>
		<th>地点</th>
		<th>概要</th>
		<th>详细信息</th>
		<th>操作</th>
	</tr>
	<s:iterator var="v" value="session.activitylist">
		<tr>
			<td class="list_data_text"><s:property value="#v.date"/></td>
			<td class="list_data_text"><s:property value="#v.place"/></td>
			<td class="list_data_ltext"><s:property value="#v.title"/></td>
			<td class="list_data_ltext"><s:property value="#v.desc"/></td>
			<td class="list_data_op">
				<img onclick="to('activity_edit?a.ID=<s:property value="#v.ID"/>');" title="编辑" src="images/bt_edit.gif" class="op_button" />
				<a href="activity_del?a.ID=<s:property value="#v.ID"/>">
					<img onclick="return warn('确定删除此条交往记录');" title="删除" src="images/bt_del.gif" class="op_button" />
				</a>
			</td>
		</tr>
	</s:iterator>
	</table>
</body>
</html>