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
<title>制定计划</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link type="text/css" href="css/calendar.css" rel="stylesheet"  />
<script type="text/javascript" src="script/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="script/calendar-zh.js" ></script>
<script type="text/javascript" src="script/call.js" charset="utf-8"></script>
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

<div class="page_title">客户开发计划 &gt;制定计划</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
	<button class="common_button" onclick="to('plan_execute?s.id=${request.editsale.id}');">执行计划</button>
</div>
	<table class="query_form_table">
		<tr>
			<th>编号</th>
			<td>${request.editsale.id}</td>
			<th>机会来源</th>
			<td>${request.editsale.source}</td>
		</tr>
		<tr>
			<th>客户名称</th>
			<td>${request.editsale.name}</td>
			<th>成功率(%)</th>
			<td>${request.editsale.rate}</td>
		</tr>	
		<tr>
			<th>概要</th>
			<td colspan="3">${request.editsale.title}</td>
		</tr>
		<tr>
			<th>联系人</th>
			<td>${request.editsale.contact}</td>
			<th>联系人电话</th>
			<td>${request.editsale.phone}</td>
		</tr>
		<tr>
			<th>机会描述</th>
			<td colspan="3">${request.editsale.desc}</td>
		</tr>
		<tr>
			<th>创建人</th>
			<td>${request.editsale.createName}</td>
			<th>创建时间</th>
			<td>${request.editsale.createTime}</td>
		</tr>
		<tr>
			<th>指派给</th>
			<td>${request.editsale.dueName}</td>
			<th>指派时间</th>
			<td>${request.editsale.dueTime}</td>
		</tr>
	</table>
	<br>
<div class="button_bar">
	<button class="common_button" onclick="check();">保存计划信息</button> 
</div>
<form action="plan_add" method="post" id="form1" >	
	<input name="s.id" value="${request.editsale.id}" readonly style="display:none"/> <!--便于添加时传递当前Sale编号  -->
	<table class="query_form_table" id="table2">
		<tr>
			<th>日期</th>
			<td>
				<input name="p.date" class="ooo" onblur="ck_null('c1','info1')" id="c1" type="date"/><span id="info1" class="red_star">*</span>
			</td>
			<th>计划项</th>
			<td>
				<input name="p.todo" size="50" onblur="ck_null('c2','info2')" id="c2"/><span id="info2" class="red_star">*</span>
			</td>
		</tr>
	</table>
</form>	
<br>
	<table class="data_list_table" id="table1">
		<tr>
			<th width="20%">日期</th>
			<th width="60%">计划项目</th>
			<th width="20%"></th>
		</tr>
	<s:iterator var="v" value="#request.planlist">
			<tr>
				<td class="list_data_text" ><s:property value='#v.date'/> </td>
				<td class="list_data_ltext">
					<form action="plan_update" method="post" id="<s:property value='#v.id'/>">
					<input name="p.todo" size="100" value="<s:property value='#v.todo'/>" />
					<button class="common_button" onclick="dosubmit('<s:property value='#v.id'/>')">修改本条</button>
					</form>	
				</td>
				<td><button class="common_button" onclick="to('plan_del?p.id=<s:property value='#v.id'/>')">删除本条</button></td>
			</tr>
	</s:iterator>
	</table>	
</body>
</html>