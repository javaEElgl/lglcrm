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
<title>执行计划</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">客户开发计划 &gt;执行计划</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
	<button class="common_button" onclick="to('plan_over?s.id=${request.editsale.id}&s.status=5')">开发失败</button>
	<button class="common_button" onclick="to('plan_over?s.id=${request.editsale.id}&s.status=6')">开发成功</button>
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
			<td colspan="3">${request.editsale.title}</td>
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
			<td class="list_data_ltext"><s:property value='#v.todo'/></td>
			<td class="list_data_ltext">
				<form action="plan_saveresult" method="post" id="<s:property value='#v.id'/>">
					<input name="p.result" value="<s:property value='#v.result'/>" type="text" />
					<button class="common_button" onclick="dosubmit('<s:property value='#v.id'/>')">保存结果</button>
				</form>	
			</td>
		</tr>
		</s:iterator>
	</table>
</body>
</html>