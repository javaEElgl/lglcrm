<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>归档详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">客户服务管理 &gt;服务归档&gt;归档详情</div>
<div class="button_bar">
	<button class="common_button" onclick="back()">返回</button>  
</div>
<table class="query_form_table">
	<tr>
		<th>编号</th>
		<td>${request.editservicer.ID}</td>
		<th>服务类型</th>
		<td>${request.editservicer.type}</td>
	</tr>
	<tr>
		<th>概要</th>
		<td colspan="3">${request.editservicer.title}</td>
	</tr>	
	<tr>
		<th>客户</th>
		<td>${request.editservicer.custname}</td>
		<th>状态</th>
		<td>已归档</td>
	</tr>	
	<tr>
		<th>服务请求</th>
		<td colspan="3">${request.editservicer.request}</td>
	</tr>
	<tr>
		<th>创建人</th>
		<td>${request.editservicer.createBy}</td>
		<th>创建时间</th>
		<td>${request.editservicer.createDate}</td>
		</tr>
		</table>
<br/>	
	<table  class="query_form_table" id="table1">
		<tr>
			<th>服务处理</th>
			<td colspan="3">${request.editservicer.deal}</td>
	</tr>
	<tr>
		<th>处理人</th>
		<td>${request.editservicer.dealby}</td>
		<th>处理时间</th>
		<td>${request.editservicer.dealDate}</td>
	</tr>
</table>
<br />
<table  class="query_form_table" >
	<tr>
		<th>分配给</th>
		<td>${request.editservicer.dueTo}</td>
		<th>分配时间</th>
		<td>${request.editservicer.dueDate}</td>
	</tr>
</table>
<br/>	
<table class="query_form_table" >
	<tr>
		<th>处理结果</th>
		<td>${request.editservicer.result}</td>
		<th>满意度</th>
		<s:if test="#request.editservicer.satisfy == 1">
		<td>☆</td>
		</s:if>
		<s:if test="#request.editservicer.satisfy == 2">
		<td>☆☆</td>
		</s:if>
		<s:if test="#request.editservicer.satisfy == 3">
		<td>☆☆☆</td>
		</s:if>
		<s:if test="#request.editservicer.satisfy == 4">
		<td>☆☆☆☆</td>
		</s:if>
		<s:if test="#request.editservicer.satisfy == 5">
		<td>☆☆☆☆☆</td>
		</s:if>
	</tr>
</table>	
</body>
</html>