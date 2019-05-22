<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>新增订单明细</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
<script type="text/javascript">
function check(){
	if(ck_number('c1','info1')&&ck_number('c2','info2')){
		dosubmit('form1');
	}
}
</script>
</head>
<body>

<div class="page_title">客户信息管理 &gt;客户信息 &gt;历史订单 &gt;新增订单明细</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>  
	<button class="common_button" onclick="check();">保存</button>  
</div>
<form action="ordersline_add" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>商品</th>
			<td>
				<select name="ol.product">
					<s:iterator var="v" value="#session.productlist">
					<option value="<s:property value='#v.name'/>"><s:property value='#v.name'/></option>
					</s:iterator>
				</select>
			</td>
			<th>数量</th>
			<td><input name="ol.count" value="" onblur="ck_number('c1','info1')" id="c1"/><span id="info1" class="red_star">*</span></td>
		</tr>
		<tr>
			<th>单位</th>
			<td><input name="ol.unit" value="" /></td>
			<th></th>
			<td></td>
		</tr>
		<tr>
			<th>单价</th>
			<td><input name="ol.price" value="" onblur="ck_number('c2','info2')" id="c2"/><span id="info2" class="red_star">*</span></td>
			<th></th>
			<td></td>
		</tr>
	</table>
</form>
</body>
</html>