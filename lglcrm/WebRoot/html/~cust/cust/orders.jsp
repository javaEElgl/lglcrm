<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>历史订单</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">客户信息管理 &gt;客户信息&gt;历史订单 </div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>  
	<button class="common_button" onclick="to('orders_toadd');">新建订单</button>  
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
		<th>订单编号</th>
		<th>日期</th>
		<th>送货地址</th>
		<th>总金额(元)</th>
		<th>状态</th>
		<th>操作</th>
	</tr>
	<s:iterator var="v" value="session.orderslist">
		<tr>
			<td class="list_data_text"><s:property value="#v.ID"/></td>
			<td class="list_data_text"><s:property value="#v.date"/></td>
			<td class="list_data_text"><s:property value="#v.addr"/></td>
			<td class="list_data_text"><s:property value="#v.money"/></td>
			<s:if test="#v.status == 1">
				<td class="list_data_text">未回款</td>
				<td class="list_data_op">
					<img onclick="to('ordersline_to?o.ID=<s:property value="#v.ID"/>');" title="订单明细" src="images/bt_detail.gif" class="op_button" />
					<img onclick="to('orders_edit?o.ID=<s:property value="#v.ID"/>');" title="编辑订单" src="images/bt_edit.gif" class="op_button" />
					<a href="orders_del?o.ID=<s:property value="#v.ID"/>"><img onclick="return warn('确定删除该订单');" title="删除订单" src="images/bt_del.gif" class="op_button"/>
					</a>
				</td>
			</s:if>
			<s:elseif test="#v.status == 2">
				<td class="list_data_text">已回款</td>
				<td class="list_data_op">
				<img onclick="to('ordersline_read?o.ID=<s:property value="#v.ID"/>');" title="查看明细" src="images/bt_detail.gif" class="op_button" />
				</td>
			</s:elseif>
		</tr>
	</s:iterator>
	</table>
</body>
</html>