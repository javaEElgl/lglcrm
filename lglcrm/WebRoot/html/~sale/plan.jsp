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
<title>客户开发计划</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">客户开发计划</div>
<div class="button_bar">
	<button class="common_button" onclick="dosubmit('form1');">查询</button> 
</div>
<form action="plan_findHQL" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>客户名称</th>
			<td><input name="s.name"  type="text"/></td>
			<th>概要</th>
			<td><input name="s.title" type="text" /></td>
			<th></th>
			<td></td>
		</tr>
	</table>
</form>	
<br />
<table class="data_list_table">
	<tr>
		<th>编号</th>
		<th>客户名称</th>
		<th>概要</th>
		<th>联系人</th>
		<th>联系人电话</th>
		<th>创建时间</th>
		<th>状态</th>
		<th>操作</th>
	</tr>
	<s:iterator var="v" value="#session.salelist">
	<tr>
		<td class="list_data_number"><s:property value="#v.id"/></td>
		<td class="list_data_text"><s:property value="#v.name"/></td>
		<td class="list_data_ltext"><s:property value="#v.title"/></td>
		<td class="list_data_text"><s:property value="#v.contact"/></td>
		<td class="list_data_text"><s:property value="#v.phone"/></td>
		<td class="list_data_text"><s:property value="#v.createTime"/></td>
		<s:if test="#v.status == 1">
			<td class="list_data_text">未指派</td>
			<td class="list_data_op">
				<img onclick="to('plan_zdjh?s.id=<s:property value="#v.id"/>');" title="制定计划" src="images/bt_plan.gif" class="op_button" />
				<img onclick="to('plan_execute?s.id=<s:property value="#v.id"/>');" title="执行计划" src="images/bt_feedback.gif" class="op_button" />
			</td>
		</s:if>
		<s:elseif test="#v.status == 2">
			<td class="list_data_text">已指派</td>
			<td class="list_data_op">
				<img onclick="to('plan_zdjh?s.id=<s:property value="#v.id"/>');" title="制定计划" src="images/bt_plan.gif" class="op_button" />
				<img onclick="to('plan_execute?s.id=<s:property value="#v.id"/>');" title="执行计划" src="images/bt_feedback.gif" class="op_button" />
			</td>
		</s:elseif>
		<s:elseif test="#v.status == 3">
			<td class="list_data_text">已计划</td>
			<td class="list_data_op">
				<img onclick="to('plan_zdjh?s.id=<s:property value="#v.id"/>');" title="制定计划" src="images/bt_plan.gif" class="op_button" />
				<img onclick="to('plan_execute?s.id=<s:property value="#v.id"/>');" title="执行计划" src="images/bt_feedback.gif" class="op_button" />
			</td>		
		</s:elseif>
		<s:elseif test="#v.status == 4">
			<td class="list_data_text">开发中</td>
			<td class="list_data_op">
				<img onclick="to('plan_zdjh?s.id=<s:property value="#v.id"/>');" title="制定计划" src="images/bt_plan.gif" class="op_button" />
				<img onclick="to('plan_execute?s.id=<s:property value="#v.id"/>');" title="执行计划" src="images/bt_feedback.gif" class="op_button" />
			</td>		
		</s:elseif>
		<s:elseif test="#v.status == 5">
			<td class="list_data_text">已归档</td>
			<td class="list_data_op">
				<img onclick="to('plan_read?s.id=<s:property value="#v.id"/>');" title="查看详情" src="images/bt_detail.gif" class="op_button" />
			</td>		
		</s:elseif>
		<s:elseif test="#v.status == 6">
			<td class="list_data_text">已归档</td>
			<td class="list_data_op">
				<img onclick="to('plan_read?s.id=<s:property value="#v.id"/>');" title="查看详情" src="images/bt_detail.gif" class="op_button" />
			</td>		
		</s:elseif>
	</tr>
	</s:iterator>
</table>
<form action="plan_find" method="post" >
	<table class="data_list_table">	
		<s:iterator value="pager">
			<tr>
				<th colspan="6" class="pager">
					<div class="pager">
						共<s:property value='count'/>条记录 &nbsp;每页<input name="pager.pageSize" value="<s:property value='pageSize'/>" size="2" />条 &nbsp; 
						第<font color="red"><s:property value='pageIndex'/></font>页/共<s:property value='pages'/>页 &nbsp;
						<a href="plan_find?pager.pageIndex=1">第一页</a>
						<a href="plan_find?pager.pageIndex=<s:property value='%{pageIndex-1}'/>">上一页</a>
						<a href="plan_find?pager.pageIndex=<s:property value='%{pageIndex+1}'/>">下一页</a>
						<a href="plan_find?pager.pageIndex=<s:property value='pages'/>">最后一页</a>
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