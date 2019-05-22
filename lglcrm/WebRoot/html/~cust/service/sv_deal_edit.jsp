<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>服务处理</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
<script type="text/javascript">
function check(){
	if(ck_null('c1','info1')){
		dosubmit('form1');
	}
}	
</script>
</head>
<body>

<div class="page_title">客户服务管理 &gt;服务处理&gt;处理详情</div>
<div class="button_bar">
	<button class="common_button" onclick="check();">保存处理</button>  
</div>
<form action="servicer_update?flag=3" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>编号</th>
			<td><input name="sv.ID" value="${request.editservicer.ID}" style="display:none" readonly/>${request.editservicer.ID}</td>
			<th>服务类型</th>
			<td>${request.editservicer.type}</td>
		</tr>
		<tr>
			<th>概要</th>
			<td colspan="3">${request.editservicer.title}</td>
		</tr>	
		<tr>
			<th>客户</th>
			<td>${request.editservicer.custname}</td>
			<th>状态</th>
			<td>已分配</td>
		</tr>	
		<tr>
			<th>服务请求</th>
			<td colspan="3">${request.editservicer.request}</td>
		</tr>
		<tr>
			<th>创建人</th>
			<td>${request.editservicer.createBy}</td>
			<th>创建时间</th>
			<td>${request.editservicer.createDate}</td>
		</tr>
		</table>
<br/>	
	<table  class="query_form_table" >
		<tr>
			<th>分配给</th>
			<td>${request.editservicer.dueTo}</td>
			<th>分配时间</th>
			<td>${request.editservicer.dueDate}</td>
		</tr>
	</table>
<br/>	
	<table  class="query_form_table" >
		<tr>
			<th>服务处理</th>
			<td colspan="3"><textarea name="sv.deal" rows="6" cols="50" onblur="ck_null('c1','info1')" id="c1"></textarea><span id="info1" class="red_star">*</span></td>
		</tr>
		<tr>
			<th>处理人</th>
			<td><input name="sv.dealby" value="${session.user.username}" readonly size="20" /></td>
			<th>处理时间</th>
			<td><input id="t1" name="sv.dealDate" value="" readonly size="20" /></td>
		</tr>
	</table>
</form>	
<script type="text/javascript">
	setCurTime('t1');
</script>	
</body>
</html>