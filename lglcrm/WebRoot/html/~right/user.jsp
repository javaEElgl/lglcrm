<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'user.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>
	<div class="page_title">用户管理</div>
	<div class="button_bar">
		<button class="common_button" onclick="to('user_toadd');">新建</button>
		<button class="common_button" onclick="javascript:document.getElementById('form1').submit();">查询</button>
	</div>
	<form action="user_findName" method="post" id="form1">
		<table class="query_form_table">
			<tr>
				<th>用户名</th>
				<td><input type="text" name="u.username" /></td>
				<th></th>
				<td></td>
			</tr>
		</table>
	</form>
	<br />
	<table class="data_list_table">
		<tr>
			<th>编号</th>
			<th>用户名</th>
			<th>操作</th>
		</tr>
		<s:iterator var="v" value="#session.userlist" status="vt">
			<tr>
				<td class="list_data_number"><s:property value="#v.userId" /></td>
				<td class="list_data_text"><s:property value="#v.username" /></td>
				<td class="list_data_op"><img
					onclick="to('user_edit?u.userId=<s:property value="#v.userId" />');"
					title="编辑用户" src="images/bt_edit.gif" class="op_button" /> <a
					href="user_del?u.userId=<s:property value="#v.userId" />&u.username=<s:property value="#v.username" />"
					onclick="return confirm('确定删除用户？');"><img title="删除用户"
						src="images/bt_del.gif" class="op_button" /></a></td>
			</tr>
		</s:iterator>
	</table>
	<form action="user_find" method="post">
		<table class="data_list_table">
			<s:iterator value="pager">
				<tr>
					<th colspan="6" class="pager">
						<div class="pager">
							共
							<s:property value='count' />
							条记录 &nbsp;每页<input name="pager.pageSize"
								value="<s:property value='pageSize'/>" size="2" />条 &nbsp; 第<font
								color="red"><s:property value='pageIndex' /></font>页/共
							<s:property value='pages' />
							页 &nbsp; <a href="user_find?pager.pageIndex=1">第一页</a> <a
								href="user_find?pager.pageIndex=<s:property value='%{pageIndex-1}'/>">上一页</a>
							<a
								href="user_find?pager.pageIndex=<s:property value='%{pageIndex+1}'/>">下一页</a>
							<a href="user_find?pager.pageIndex=<s:property value='pages'/>">最后一页</a>
							&nbsp;转到<input name="pager.pageIndex" size="2" />页 <input
								type="submit" value="GO" size="2" />
						</div>
					</th>
				</tr>
			</s:iterator>
		</table>
	</form>
</body>
</html>