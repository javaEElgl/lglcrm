<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>反馈详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
<script type="text/javascript">
function check(){
	if(ck_null('c1','info1')&&ck_null('c2','info2')){
		dosubmit('form1');
	}
}	
</script>
</head>
<body>

<div class="page_title">客户服务管理 &gt;服务反馈&gt;反馈详情</div>
<div class="button_bar">
	<button class="common_button" onclick="check();">保存反馈</button>  
</div>
<form action="servicer_update?flag=4" method="post" id="form1">
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
			<td>已处理</td>
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
	<table  class="query_form_table" id="table1">
		<tr>
			<th>服务处理</th>
			<td colspan="3">${request.editservicer.deal}</td>
		</tr>
		<tr>
			<th>处理人</th>
			<td>${request.editservicer.dealby}</td>
			<th>处理时间</th>
			<td>${request.editservicer.dealDate}</td>
		</tr>
	</table>
<br />
	<table  class="query_form_table" >
		<tr>
			<th>分配给</th>
			<td>${request.editservicer.dueTo}</td>
			<th>分配时间</th>
			<td>${request.editservicer.dueDate}</td>
		</tr>
	</table>
<br/>	
<table class="query_form_table" >
	<tr>
		<th>处理结果</th>
		<td><input name="sv.result" size="30" onblur="ck_null('c1','info1')" id="c1" /><span id="info1" class="red_star">*</span></td>
		<th>满意度</th>
		<td>
			<select name="sv.satisfy" onblur="ck_null('c2','info2')" id="c2">
				<option value="">请选择...</option>
				<option value="1">☆</option>
				<option value="2">☆☆</option>
				<option value="3">☆☆☆</option>
				<option value="4">☆☆☆☆</option>
				<option value="5">☆☆☆☆☆</option>
			</select>
			<span id="info2" class="red_star">*</span>
		</td>
	</tr>
</table>	
</form>		
</body>
</html>