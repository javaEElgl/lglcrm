<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>服务分配</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>

</head>
<body>

<div class="page_title">客户服务管理&gt;服务分配</div>
<div class="button_bar">
	<button class="common_button" onclick="dosubmit('form1');">查询</button>  
</div>
<form action="servicer_findHQL?flag=2" method="post" id="form1">
	<table class="query_form_table" >
		<tr>
			<th height="28">客户</th>
			<td><input name="sv.custname" value=""/></td>
			<th height="28">服务类型</th>
			<td>
				<select name="sv.type">
					<option value="">全部</option>
					<s:iterator var="v" value="#session.dictlist">
						<s:if test='#v.type=="服务类型"'>
							<option value="<s:property value='#v.item'/>"><s:property value='#v.item'/></option>
						</s:if>
					</s:iterator>
				</select>
			</td>
		</tr>
	</table>
</form>
<br />
<table class="data_list_table">
	<tr>
		<th>编号</th>
		<th>客户</th>
		<th>概要</th>
		<th>服务类型</th>
		<th>创建人</th>
		<th>创建日期</th>
		<th>状态</th>
		<th>操作</th>
	</tr>
	<s:iterator var="v" value="#session.servicerlist">
		<s:if test="#v.status == 1">
			<tr>
				<td class="list_data_number"><s:property value='#v.ID'/></td>
				<td class="list_data_text"><s:property value='#v.custname'/></td>
				<td class="list_data_ltext"><s:property value='#v.title'/></td>
				<td class="list_data_text"><s:property value='#v.type'/></td>
				<td class="list_data_text"><s:property value='#v.createBy'/></td>
				<td class="list_data_text"><s:property value='#v.createDate'/></td>
				<td class="list_data_text">未分配</td>
				<td class="list_data_op">
					<a href="servicer_del?flag=2&sv.ID=<s:property value='#v.ID'/>"><img onclick="return warn('确定删除本条信息？');" title="删除" src="images/bt_del.gif" class="op_button" /></a>  
					<button class="common_button" onclick="to('servicer_edit?flag=2&sv.ID=<s:property value='#v.ID'/>');">分配</button>  
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr>
				<td class="list_data_number"><s:property value='#v.ID'/></td>
				<td class="list_data_text"><s:property value='#v.custname'/></td>
				<td class="list_data_ltext"><s:property value='#v.title'/></td>
				<td class="list_data_text"><s:property value='#v.type'/></td>
				<td class="list_data_text"><s:property value='#v.createBy'/></td>
				<td class="list_data_text"><s:property value='#v.createDate'/></td>
				<s:if test="#v.status == 2">
					<td class="list_data_text">已分配</td>
					<td class="list_data_op">
					<a href="servicer_del?flag=2&sv.ID=<s:property value='#v.ID'/>"><img onclick="return warn('确定删除本条信息？')" title="删除" src="images/bt_del.gif" class="op_button" /></a>  
					</td>
				</s:if>
				<s:if test="#v.status == 3">
					<td class="list_data_text">已处理</td>
					<td class="list_data_op"></td>
				</s:if>
				<s:if test="#v.status == 4">
					<td class="list_data_text">已归档</td>
					<td class="list_data_op"></td>
				</s:if>
			</tr>
		</s:else>
	</s:iterator>
</table>
<form action="servicer_find?flag=2" method="post" >
	<table class="data_list_table">	
		<s:iterator value="pager">
			<tr>
				<th colspan="6" class="pager">
					<div class="pager">
						共<s:property value='count'/>条记录 &nbsp;每页<input name="pager.pageSize" value="<s:property value='pageSize'/>" size="2" />条 &nbsp; 
						第<font color="red"><s:property value='pageIndex'/></font>页/共<s:property value='pages'/>页 &nbsp;
						<a href="servicer_find?flag=2&pager.pageIndex=1">第一页</a>
						<a href="servicer_find?flag=2&pager.pageIndex=<s:property value='%{pageIndex-1}'/>">上一页</a>
						<a href="servicer_find?flag=2&pager.pageIndex=<s:property value='%{pageIndex+1}'/>">下一页</a>
						<a href="servicer_find?flag=2&pager.pageIndex=<s:property value='pages'/>">最后一页</a>
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