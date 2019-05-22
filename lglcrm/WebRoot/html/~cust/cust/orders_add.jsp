<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>新建订单</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link type="text/css" href="css/calendar.css" rel="stylesheet"  />
<script type="text/javascript" src="script/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="script/calendar-zh.js" ></script>
<script type="text/javascript" src="script/call.js" charset="utf-8"></script>
<script src="script/common.js"></script>
<script type="text/javascript">
function check(){
	if(ck_null('c1','info1')&&ck_number('c2','info2')){
		dosubmit('form1');
	}
}
</script>
</head>
<body>

<div class="page_title">客户信息管理 &gt;客户信息&gt;订单信息&gt;新建订单</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
	<button class="common_button" onclick="check();">保存</button>  
</div>
<form action="orders_add" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>订单日期</th>
			<td>
				<input name="o.date" class="ooo" readonly="readonly" value="" onblur="ck_null('c1','info1')" id="c1"/>
				<input type="button" class="kkk" value="请选择日期" onclick="datepicker()"/><span id="info1" class="red_star">*</span>
			</td>
			<th>订单状态</th>
			<td>
				<select name="o.status">
					<option value="1">未回款</option>
					<option value="2">已回款</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>送货地址</th>
			<td><input name="o.addr" value="" /></td>
			<th>总金额</th>
			<td><input name="o.money" value="" onblur="ck_number('c2','info2')" id="c2"/><span id="info2" class="red_star">*</span></td>
		</tr>	
	</table>
</form>
</body>
</html>