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
<title>新建角色</title>
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

<div class="page_title">角色授权管理&gt;新建角色</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
	<button class="common_button" onclick="check();">保存</button>  
</div>
<form action="role_add" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>角色名</th>
			<td><input type="text" name="r.roleName" onblur="ck_null('c1','info1')" id="c1"/><font id="info1" color="red"> *</font></td>
			<th></th>
			<td></td>
		</tr>
		<tr>
			<th>授予权限</th>
			<td colspan="3">
				<br/>
			 	<input type="checkbox" onclick="sel('rightCode')">[全部选择/取消全部 ]<br/>
				<br/>
			 	<input type="checkbox" name="rightCode" value="1">销售机会管理 <br/>
			 	<input type="checkbox" name="rightCode" value="2">客户开发计划<br/>
			 	<input type="checkbox" name="rightCode" value="3">客户信息管理<br/>
			 	<input type="checkbox" name="rightCode" value="4">客户流失管理<br/>
			 	<input type="checkbox" name="rightCode" value="5">服务创建<br/>
			 	<input type="checkbox" name="rightCode" value="6">服务分配<br/>
			 	<input type="checkbox" name="rightCode" value="7">服务处理<br/>
			 	<input type="checkbox" name="rightCode" value="8">服务反馈<br/>
			 	<input type="checkbox" name="rightCode" value="9">服务归档<br/>
			 	<input type="checkbox" name="rightCode" value="10">客户贡献分析<br/>
			 	<input type="checkbox" name="rightCode" value="11">客户构成分析<br/>
			 	<input type="checkbox" name="rightCode" value="12">客户服务分析<br/>
			 	<input type="checkbox" name="rightCode" value="13">客户流失分析<br/>
			 	<input type="checkbox" name="rightCode" value="14">数据字典管理<br/>
			 	<input type="checkbox" name="rightCode" value="15">查询产品信息<br/>
			 	<input type="checkbox" name="rightCode" value="16">查询库存<br/>
			 	<input type="checkbox" name="rightCode" value="17">用户管理<br/>
			 	<input type="checkbox" name="rightCode" value="18">角色授权管理<br/>
			 	<br/>
			</td>
		</tr>
	</table>
</form>	
</body>
</html>