<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>联系人</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">客户信息管理 &gt;客户信息 &gt;联系人 </div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>  
	<button class="common_button" onclick="to('linkman_toadd');">添加联系人</button>  
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
		<th>姓名</th>
		<th>性别</th>
		<th>职位</th>
		<th>办公电话</th>
		<th>手机</th>
		<th>备注</th>
		<th>操作</th>
	</tr>
	<s:iterator var="v" value="session.linkmanlist">
		<tr>
			<td class="list_data_text"><s:property value="#v.name"/></td>
			<td class="list_data_ltext"><s:property value="#v.sex"/></td>
			<td class="list_data_text"><s:property value="#v.postion"/></td>
			<td class="list_data_text"><s:property value="#v.tel"/></td>
			<td class="list_data_text"><s:property value="#v.phone"/></td>
			<td class="list_data_op"><s:property value="#v.memo"/></td>
			<td class="list_data_op">
				<img onclick="to('linkman_edit?l.ID=<s:property value="#v.ID"/>');" title="编辑" src="images/bt_edit.gif" class="op_button" />
				<a href="linkman_del?l.ID=<s:property value="#v.ID"/>">
					<img onclick="return warn('确定删除该联系人');" title="删除" src="images/bt_del.gif" class="op_button" />
				</a>
			</td>
		</tr>
	</s:iterator>
	</table>
</body>
</html>