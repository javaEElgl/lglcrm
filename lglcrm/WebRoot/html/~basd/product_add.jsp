<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加产品</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
<script type="text/javascript">
function check(){
	if(ck_null('c1','info1')&&ck_number('c2','info2')&&ck_null('c3','info3')&&ck_number('c4','info4')&&ck_null('c5','info5')){
		dosubmit('form1');
	}
}
</script>
</head>
<body>

<div class="page_title">添加产品</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
	<button class="common_button" onclick="check();">保存产品</button>  
</div>
<form action="product_add" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>商品名字</th>
			<td><input name="pr.name"  value="" onblur="ck_null('c1','info1')" id="c1"/><span id="info1" class="red_star">*</span></td>
			<th>型号</th>
			<td><input name="pr.type"   /></td>
			<th>等级/批次</th>
			<td><input name="pr.batch"  ></td>
		</tr>
		<tr>
			<th>单价</th>
			<td><input name="pr.price" value="" onblur="ck_number('c2','info2')" id="c2"/><span id="info2" class="red_star">*</span></td>
			<th>单位</th>
			<td><input name="pr.unit" value="" onblur="ck_null('c3','info3')" id="c3"/><span id="info3" class="red_star">*</span></td>
			<th>备注</th>
			<td><input name="pr.memo" /></td>
		</tr>	
		<tr>
			<th>数量</th>
			<td><input name="pr.count" value="" onblur="ck_number('c4','info4')" id="c4"/><span id="info4" class="red_star">*</span></td>
			<th>仓库</th>
			<td><input name="pr.storageName" value="" onblur="ck_null('c5','info5')" id="c5"/><span id="info5" class="red_star">*</span></td>
			<th>货位</th>
			<td><input name="pr.ware" /></td>
		</tr>	
	</table>
</form>
</body>
</html>