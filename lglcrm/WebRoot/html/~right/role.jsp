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
<title>角色授权管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">角色授权管理</div>
<div class="button_bar">
	<button class="common_button" onclick="to('role_toadd');">新建角色</button>
	<button class="common_button" onclick="dosubmit('form1');">查询</button>  
</div>
<form action="role_findHQL" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>角色名</th>
			<td><input type="text" name="r.roleName" /></td>
			<th></th>
			<td></td>
		</tr>
	</table>
</form>	
<br />
<table class="data_list_table">
	<tr>
		<th>编号</th>
		<th>角色名</th>
		<th>操作</th>
	</tr>
	<s:iterator var="r" value="#session.rolelist" >
		<tr>
			<td class="list_data_number"><s:property value="#r.roleId" /> </td>
			<td class="list_data_text"><s:property value="#r.roleName" /></td>
			<td class="list_data_op">　
				<img onclick="to('role_edit?r.roleId=<s:property value="#r.roleId" />');" title="编辑权限" src="images/bt_edit.gif" class="op_button" />
				<a href="role_del?r.roleId=<s:property value="#r.roleId"/>&r.roleName=<s:property value="#r.roleName"/> "><img onclick="return warn('确定删除角色？');" title="删除角色" src="images/bt_del.gif" class="op_button" /></a>
			</td>
		</tr>
	</s:iterator>
</table>
<form action="role_page" method="post" >
	<table class="data_list_table">	
		<s:iterator value="pager">
			<tr>
				<th colspan="6" class="pager">
					<div class="pager">
						共<s:property value='count'/>条记录 &nbsp;每页<input name="pager.pageSize" value="<s:property value='pageSize'/>" size="2" />条 &nbsp; 
						第<font color="red"><s:property value='pageIndex'/></font>页/共<s:property value='pages'/>页 &nbsp;
						<a href="role_find?pager.pageIndex=1">第一页</a>
						<a href="role_find?pager.pageIndex=<s:property value='%{pageIndex-1}'/>">上一页</a>
						<a href="role_find?pager.pageIndex=<s:property value='%{pageIndex+1}'/>">下一页</a>
						<a href="role_find?pager.pageIndex=<s:property value='pages'/>">最后一页</a>
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