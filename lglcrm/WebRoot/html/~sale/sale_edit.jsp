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
<title>编辑销售机会</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
<script type="text/javascript">
function check(){
	if(ck_null('c1','info1')&&ck_number('c2','info2')&&ck_null('c3','info3')&&ck_null('c4','info4')){
		dosubmit('form1');
	}
}
</script>
</head>
<body>

<div class="page_title">销售机会管理&gt;编辑销售机会</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
	<button class="common_button" onclick="check();">保存编辑</button>  
</div>
<form action="sale_update" method="post" id="form1" >
	<table class="query_form_table">
		<tr>
			<th>编号</th>
			<td><input name="s.id" value="${request.editsale.id}" readonly style="display:none"/>${request.editsale.id}</td>
			<th>机会来源</th>
			<td><input name="s.source" value="${request.editsale.source}" /></td>
		</tr>
		<tr>
			<th>客户名称</th>
			<td><input name="s.name" value="${request.editsale.name}" type="text" size="40" onblur="ck_null('c1','info1')" id="c1"/><span id="info1" class="red_star">*</span></td>
			<th>成功率(%)</th>
			<td><input name="s.rate" value="${request.editsale.rate}" type="text" onblur="ck_number('c2','info2')" id="c2"/><span id="info2" class="red_star">*</span></td>
		</tr>	
		<tr>
			<th>概要</th>
			<td colspan="3"><input name="s.title" value="${request.editsale.title}" type="text" size="70" onblur="ck_null('c3','info3')" id="c3"/><span id="info3" class="red_star">*</span></td>
		</tr>
		<tr>
			<th>联系人</th>
			<td><input name="s.contact" value="${request.editsale.contact}" type="text" size="20" /></td>
			<th>联系人电话</th>
			<td><input name="s.phone" value="${request.editsale.phone}" type="text" size="20" /></td>
		</tr>
		<tr>
			<th>机会描述</th>
			<td colspan="3"><textarea rows="6" cols="50" name="s.desc" onblur="ck_null('c4','info4')" id="c4">${request.editsale.desc}</textarea><span id="info4" class="red_star">*</span></td>
		</tr>
		<tr>
			<th>创建人</th>
			<td><input name="s.createName" value="${request.editsale.createName}" readonly style="display:none" /> ${request.editsale.createName}</td>
			<th>创建时间</th>
			<td><input name="s.createTime" value="${request.editsale.createTime}" readonly style="display:none" /> ${request.editsale.createTime}</td>
		</tr>
		<tr>
			<th>重新指派给</th>
			<td>
				<select name="s.dueName">
					<option value="未指派">请选择...</option>
					<s:iterator var="v" value="#session.userlist">
					<option value="<s:property value='#v.username'/>"><s:property value='#v.username'/></option>
					</s:iterator>
				</select>
			</td>
			<th></th>
			<td></td>
		</tr>
		<tr>
			<th>上次指派给</th>
			<td>${request.editsale.dueName}</td>
			<th>指派时间</th>
			<td>${request.editsale.dueTime}</td>
		</tr>
		
	</table>
</form>
</body>
</html>