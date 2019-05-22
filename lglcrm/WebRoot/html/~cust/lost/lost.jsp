<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>客户流失管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">客户流失管理</div>
<div class="button_bar">
	<button class="common_button" onclick="dosubmit('form1');">查询</button>  
	<button class="common_button" onclick="to('lost_toadd');">新建</button>  
</div>
<form action="lost_findHQL" method="post" id="form1">
	<table class="query_form_table">
	<tr>
		<th>客户</th>
		<td><input name="l.customer" value=""/></td>
		<th>客户经理</th>
		<td><input name="l.manager" value="" /></td>
		<th></th>
		<td></td>
	</tr>
	</table>
</form>	
<br />
	<table class="data_list_table">
		<tr>
			<th>编号</th>
			<th>客户</th>
			<th>客户经理</th>
			<th>上次下单时间</th>
			<th>确认流失时间</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<s:iterator var="v" value="#session.lostlist">
		<tr>
			<td class="list_data_number"><s:property value='#v.ID'/></td>
			<td class="list_data_text"><s:property value='#v.customer'/></td>
			<td class="list_data_ltext"><s:property value='#v.manager'/></td>
			<td class="list_data_text"><s:property value='#v.orderdate'/></td>
			<td class="list_data_text"><s:property value='#v.lostdate'/></td>
			
			<s:if test="#v.status == 1">
				<td class="list_data_text">预警</td>
				<td class="list_data_op">
				<img onclick="to('lost_edit?flag=33&l.ID=<s:property value='#v.ID'/>');" title="确认流失" src="images/bt_confirm.gif" class="op_button" />
				<img onclick="to('lost_edit?flag=22&l.ID=<s:property value='#v.ID'/>');" title="暂缓流失" src="images/bt_relay.gif" class="op_button" />
				</td>
			</s:if>
			<s:elseif test="#v.status == 2">
				<td class="list_data_text">暂缓流失</td>
				<td class="list_data_op">
				<img onclick="to('lost_edit?flag=33&l.ID=<s:property value='#v.ID'/>');" title="确认流失" src="images/bt_confirm.gif" class="op_button" />
				<img onclick="to('lost_edit?flag=22&l.ID=<s:property value='#v.ID'/>');" title="暂缓流失" src="images/bt_relay.gif" class="op_button" />
				</td>
			</s:elseif>
			<s:elseif test="#v.status == 3">
				<td class="list_data_text">已经流失</td>
				<td class="list_data_op">
					<img onclick="to('lost_edit?flag=11&l.ID=<s:property value='#v.ID'/>');" title="查看详情" src="images/bt_detail.gif" class="op_button" />
				</td>
			</s:elseif>
		</tr>
		</s:iterator>
	</table>
<form action="lost_find" method="post" >
	<table class="data_list_table">	
		<s:iterator value="pager">
			<tr>
				<th colspan="6" class="pager">
					<div class="pager">
						共<s:property value='count'/>条记录 &nbsp;每页<input name="pager.pageSize" value="<s:property value='pageSize'/>" size="2" />条 &nbsp; 
						第<font color="red"><s:property value='pageIndex'/></font>页/共<s:property value='pages'/>页 &nbsp;
						<a href="lost_find?pager.pageIndex=1">第一页</a>
						<a href="lost_find?pager.pageIndex=<s:property value='%{pageIndex-1}'/>">上一页</a>
						<a href="lost_find?pager.pageIndex=<s:property value='%{pageIndex+1}'/>">下一页</a>
						<a href="lost_find?pager.pageIndex=<s:property value='pages'/>">最后一页</a>
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