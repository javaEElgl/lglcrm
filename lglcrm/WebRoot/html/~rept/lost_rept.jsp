<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>客户流失分析</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">客户流失分析</div>
<div class="button_bar">
	<button class="common_button" onclick="dosubmit('form1')">查询</button> 
	</div>
<form action="lost_rept" method="post" id="form1">
<table class="query_form_table">
	<tr>
		<th>年份</th>
		<td>
			<select name="flag">
				<option value="">全部</option>
				<option value="2015">2015</option>
				<option value="2014">2014</option>
				<option value="2013">2013</option>
				<option value="2012">2012</option>
			</select>
		</td>
		<th></th>
		<td></td>
	</tr>
</table>
</form>	
<br />
<table class="data_list_table">
	<tr>
		<th >序号</th>
		<th >流失时间</th>
		<th >客户</th>
		<th >客户经理</th>
		<th >流失原因</th>
	</tr>
	<s:iterator var="v" value="#request.lostRept" status="st">
	<tr>
		<td class="list_data_number"><s:property value="#st.index"/></td>
		<td class="list_data_text"><s:property value="#v.lostdate"/></td>
		<td class="list_data_number"><s:property value="#v.customer"/></td>
		<td class="list_data_number"><s:property value="#v.manager"/></td>
		<td class="list_data_number"><s:property value="#v.reason"/></td>
	</tr>
	</s:iterator>
</table>
</body>
</html>