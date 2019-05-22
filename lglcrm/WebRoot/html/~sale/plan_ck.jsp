<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>已归档</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">客户开发计划 &gt;已归档信息查看</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
</div>
	<table class="query_form_table">
		<tr>
			<th>编号</th>
			<td>${request.editsale.id}</td>
			<th>机会来源</th>
			<td>${request.editsale.source}</td>
		</tr>
		<tr>
			<th>客户名称</th>
			<td>${request.editsale.name}</td>
			<th>成功率(%)</th>
			<td>${request.editsale.rate}</td>
		</tr>	
		<tr>
			<th>概要</th>
			<td>${request.editsale.title}</td>
			<th>状态</th>
			<c:if test="${request.editsale.status == 5}">
			<td><font color="red">开发失败</font></td>
			</c:if>
			<c:if test="${request.editsale.status == 6}">
			<td><font color="red">开发成功</font></td>
			</c:if>
		</tr>
		<tr>
			<th>联系人</th>
			<td>${request.editsale.contact}</td>
			<th>联系人电话</th>
			<td>${request.editsale.phone}</td>
		</tr>
		<tr>
			<th>机会描述</th>
			<td colspan="3">${request.editsale.desc}</td>
		</tr>
		<tr>
			<th>创建人</th>
			<td>${request.editsale.createName}</td>
			<th>创建时间</th>
			<td>${request.editsale.createTime}</td>
		</tr>
		<tr>
			<th>指派给</th>
			<td>${request.editsale.dueName}</td>
			<th>指派时间</th>
			<td>${request.editsale.dueTime}</td>
		</tr>
	</table>
	<br>
	<table class="data_list_table" id="table1">
		<tr>
			<th >日期</th>
			<th >计划项目</th>
			<th >计划效果</th>
		</tr>
		<s:iterator var="v" value="#request.planlist">
		<tr>
			<td class="list_data_text" ><s:property value='#v.date'/> </td>
			<td class="list_data_ltext"><s:property value='#v.todo'/>
			</td>
			<td class="list_data_ltext"><s:property value='#v.result'/>
			</td>
		</tr>
		</s:iterator>
	</table>
</body>
</html>