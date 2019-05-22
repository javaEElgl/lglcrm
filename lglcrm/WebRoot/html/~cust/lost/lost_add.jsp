<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>新建流失</title>
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

<div class="page_title">客户流失管理 &gt;新建流失信息</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
	<button class="common_button" onclick="check();">保存信息</button>
</div>
<form action="lost_add" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>编号</th>
			<td></td>
			<th>客户</th>
			<td>
				<select name="l.customer">
					<s:iterator var="v" value="#session.customerlist">
						<option value="<s:property value='#v.name'/>"><s:property value='#v.name'/></option>
					</s:iterator>
				</select>
			</td>
		</tr>
		<tr>
			<th>客户经理</th>
			<td><input name="l.manager" value="" onblur="ck_null('c1','info1')" id="c1"/><span id="info1" class="red_star">*</span></td>
			<th>上次下单时间</th>
			<td>
				<input name="l.orderdate" class="ooo" readonly="readonly" value="" onblur="ck_null('c2','info2')" id="c2"/>
				<input type="button" class="kkk" value="请选择日期" onclick="datepicker()"/><span id="info2" class="red_star">*</span>
			</td>
		<tr>
			<th>暂缓措施</th>
			<td colspan="3">
				<textarea  name="l.delay" rows="6" cols="50"></textarea></td>
		</tr>
	</table>
</form>
<br />
</body>
</html>