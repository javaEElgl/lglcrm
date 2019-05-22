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
<title>数据字典</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">数据字典管理</div>
<div class="button_bar">
	<button class="common_button" onclick="to('dict_toadd');">新建</button>
	<button class="common_button" onclick="dosubmit('form1');">查询</button>  
</div>
<form action="dict_findHQL" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>类别</th>
			<td><input name="d.type" value="" type="text"/></td>
			<th>条目</th>
			<td><input name="d.item" value="" type="text"/></td>
			<th>值</th>
			<td><input name="d.value" value="" type="text"/></td>
		</tr>
	</table>
</form>	
<br />
<table class="data_list_table">
	<tr>
		<th>编号</th>
		<th>类别</th>
		<th>条目</th>
		<th>值</th>
		<th>是否可编辑</th>
		<th>操作</th>
	</tr>
	<s:iterator var="v" value="#session.dictlist">
	<tr>
		<td class="list_data_number"><s:property value="#v.ID"/></td>
		<td class="list_data_ltext"><s:property value="#v.type"/></td>
		<td class="list_data_text"><s:property value="#v.item"/></td>
		<td class="list_data_text"><s:property value="#v.value"/></td>
		<td class="list_data_text"><s:property value="#v.isedit"/></td>
		<s:if test='#v.isedit=="是"'>
			<td class="list_data_op">
				<img onclick="to('dict_edit?d.ID=<s:property value="#v.ID"/>');" title="编辑" src="images/bt_edit.gif" class="op_button" />
				<a href="dict_del?d.ID=<s:property value="#v.ID"/>"><img onclick="return warn('确定删除该条数据？');" title="删除" src="images/bt_del.gif" class="op_button" /></a>
			</td>
		</s:if>
		<s:else>
			<td class="list_data_op"></td>
		</s:else>
	</tr>
	</s:iterator>
</table>
<form action="dict_find" method="post" >
	<table class="data_list_table">	
		<s:iterator value="pager">
			<tr>
				<th colspan="6" class="pager">
					<div class="pager">
						共<s:property value='count'/>条记录 &nbsp;每页<input name="pager.pageSize" value="<s:property value='pageSize'/>" size="2" />条 &nbsp; 
						第<font color="red"><s:property value='pageIndex'/></font>页/共<s:property value='pages'/>页 &nbsp;
						<a href="dict_find?pager.pageIndex=1">第一页</a>
						<a href="dict_find?pager.pageIndex=<s:property value='%{pageIndex-1}'/>">上一页</a>
						<a href="dict_find?pager.pageIndex=<s:property value='%{pageIndex+1}'/>">下一页</a>
						<a href="dict_find?pager.pageIndex=<s:property value='pages'/>">最后一页</a>
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