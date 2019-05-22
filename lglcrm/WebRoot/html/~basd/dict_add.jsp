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
<title>新建数据字典</title>
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

<div class="page_title">数据字典管理 &gt;新建数据字典条目</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
	<button class="common_button" onclick="check();">保存新建</button>  
</div>
<form action="dict_add" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>编号</th>
			<td></td>
			<th>类别</th>
			<td>
				<select name="d.type">
					<option value="客户等级">客户等级</option>
					<option value="服务类型">服务类型</option>
					<option value="地区">地区</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>条目</th>
			<td><input name="d.item" onblur="ck_null('c1','info1')" id="c1"/><span id="info1" class="red_star">*</span></td>
			<th>值</th>
			<td><input name="d.value" /></td>
		</tr>	
		<tr>
			<th>是否可编辑</th>
			<td>
				<select name="d.isedit">
					<option value="是">是</option>
					<option value="否">否</option>
				</select>
			</td>
			<th></th>
			<td></td>
		</tr>
	</table>
</form>
</body>
</html>