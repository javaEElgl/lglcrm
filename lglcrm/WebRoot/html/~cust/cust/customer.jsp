<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>客户信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">客户信息管理</div>
<div class="button_bar">
	<button class="common_button" onclick="to('customer_toadd');">新建</button>
	<button class="common_button" onclick="dosubmit('form1');">查询</button>  
</div>
<form action="customer_findHQL" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>名称</th>
			<td><input name="c.name" value=""/></td>
			<th>客户编号</th>
			<td><input name="c.no" value=""/></td>
			<th></th>
			<td></td>
	</table>
</form>
<br />
<table class="data_list_table">
	<tr>
		<th>序号</th>
		<th>客户编号</th>
		<th>名称</th>
		<th>地区</th>
		<th>客户经理</th>
		<th>客户等级</th>
		<th>操作</th>
	</tr>
	<s:iterator var="v" value="#session.customerlist">
	<tr>
		<td class="list_data_number"><s:property value="#v.ID"/></td>
		<td class="list_data_text"><s:property value="#v.no"/></td>
		<td class="list_data_ltext"><s:property value="#v.name"/></td>
		<td class="list_data_text"><s:property value="#v.region"/></td>
		<td class="list_data_text"><s:property value="#v.manager"/></td>
		<td class="list_data_text"><s:property value="#v.level"/></td>
		<td class="list_data_op">
			<img onclick="to('customer_edit?c.ID=<s:property value="#v.ID"/>');" title="编辑" src="images/bt_edit.gif" class="op_button" />
			<img onclick="to('linkman_to?c.ID=<s:property value="#v.ID"/>');" title="联系人" src="images/bt_linkman.gif" class="op_button" />
			<img onclick="to('activity_to?c.ID=<s:property value="#v.ID"/>');" title="交往记录" src="images/bt_acti.gif" class="op_button" />
			<img onclick="to('orders_to?c.ID=<s:property value="#v.ID"/>');" title="历史订单" src="images/bt_orders.gif" class="op_button" />
			<a href="customer_del?c.ID=<s:property value='#v.ID'/>">
			<img onclick="return warn('确定删除该条客户信息？');" title="删除" src="images/bt_del.gif" class="op_button" />
			</a>
		</td>
	</tr>
	</s:iterator>
</table>
<form action="customer_find" method="post" >
	<table class="data_list_table">	
		<s:iterator value="pager">
			<tr>
				<th colspan="6" class="pager">
					<div class="pager">
						共<s:property value='count'/>条记录 &nbsp;每页<input name="pager.pageSize" value="<s:property value='pageSize'/>" size="2" />条 &nbsp; 
						第<font color="red"><s:property value='pageIndex'/></font>页/共<s:property value='pages'/>页 &nbsp;
						<a href="customer_find?pager.pageIndex=1">第一页</a>
						<a href="customer_find?pager.pageIndex=<s:property value='%{pageIndex-1}'/>">上一页</a>
						<a href="customer_find?pager.pageIndex=<s:property value='%{pageIndex+1}'/>">下一页</a>
						<a href="customer_find?pager.pageIndex=<s:property value='pages'/>">最后一页</a>
						 &nbsp;转到<input name="pager.pageIndex"  size="2" />页
						<input type="submit" value="GO" size="2" />
					</div>
				</th>
			</tr>
		</s:iterator>
	</table>
</form>	
</body>
</html>