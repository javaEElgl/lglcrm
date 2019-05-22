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
<title>产品查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>

<div class="page_title">产品库存</div>
<div class="button_bar">
	<button class="common_button" onclick="dosubmit('form1');">查询</button>  
</div>
<form action="product_findHQL" method="post" id="form1" >
	<table class="query_form_table">
		<tr>
			<th>名称</th>
			<td><input name="pr.name" value="" type="text" /></td>
			<th>仓库</th>
			<td><input name="pr.storageName" value="" type="text" /></td>
			<th><input name="flag" type="text" value="storage" readonly="readonly" style="display:none" /></th>
			<td></td>
		</tr>
	</table>
</form>

<br />
<table class="data_list_table">
	<tr>
		<th>序号</th>
		<th>产品</th>
		<th>仓库</th>
		<th>货位</th>
		<th>件数</th>
		<th>备注</th>
	</tr>
	<s:iterator var="v" value="#session.productlist">
	<tr>
		<td class="list_data_number"><s:property value="#v.ID"/></td>
		<td class="list_data_ltext"><s:property value="#v.name"/></td>
		<td class="list_data_ltext"><s:property value="#v.storageName"/></td>
		<td class="list_data_text"><s:property value="#v.ware"/></td>
		<td class="list_data_number"><s:property value="#v.count"/></td>
		<td class="list_data_ltext"><s:property value="#v.memo"/></td>		
	</tr>
	</s:iterator>
</table>
<form action="product_find" method="post" >
	<table class="data_list_table">	
		<s:iterator value="pager">
			<tr>
				<th colspan="6" class="pager">
					<div class="pager">
						共<s:property value='count'/>条记录 &nbsp;每页<input name="pager.pageSize" value="<s:property value='pageSize'/>" size="2" />条 &nbsp; 
						第<font color="red"><s:property value='pageIndex'/></font>页/共<s:property value='pages'/>页 &nbsp;
						<a href="product_find?pager.pageIndex=1">第一页</a>
						<a href="product_find?pager.pageIndex=<s:property value='%{pageIndex-1}'/>">上一页</a>
						<a href="product_find?pager.pageIndex=<s:property value='%{pageIndex+1}'/>">下一页</a>
						<a href="product_find?pager.pageIndex=<s:property value='pages'/>">最后一页</a>
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