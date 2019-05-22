<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>服务创建</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
<script type="text/javascript">
function check(){
	if(ck_null('c1','info1')&&ck_null('c2','info2')&&ck_null('c3','info3')&&ck_null('c4','info4')){
		dosubmit('form1');
	}
}	
</script>
</head>
<body>

<div class="page_title">客户服务管理 &gt;服务创建</div>
<div class="button_bar">
	<button class="common_button" onclick="check();">确定创建</button>  
</div>
<form action="servicer_add?flag=2" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>编号</th>
			<td></td>
			<th>服务类型</th>
				<td>
					<select name="sv.type" onblur="ck_null('c1','info1')" id="c1">
						<option value="">请选择...</option>
					<s:iterator var="v" value="#session.dictlist">
						<s:if test='#v.type=="服务类型"'>
							<option value="<s:property value='#v.item'/>"><s:property value='#v.item'/></option>
						</s:if>
					</s:iterator>
					</select><span id="info1" class="red_star">*</span>
				</td>
		</tr>
		<tr>
			<th>概要</th>
			<td colspan="3"><input name="sv.title" size="53" onblur="ck_null('c2','info2')" id="c2"><span id="info2" class="red_star">*</span></td>
		</tr>	
		<tr>
			<th>客户</th>
			<td> 
				<select name="sv.custname" onblur="ck_null('c3','info3')" id="c3">
						<option value="">请选择...</option>
					<s:iterator var="v" value="#session.customerlist">
						<option value="<s:property value='#v.name'/>"><s:property value='#v.name'/></option>
					</s:iterator>
					</select><span id="info3" class="red_star">*</span>
			</td>
			<th>状态</th>
			<td>新创建</td>
		</tr>	
		<tr>
			<th>服务请求</th>
			<td colspan="3"><textarea name="sv.request" rows="6" cols="50" onblur="ck_null('c4','info4')" id="c4"></textarea><span id="info4" class="red_star">*</span></td>
		</tr>
		<tr>
			<th>创建人</th>
			<td><input name="sv.createBy" value="${session.user.username}" readonly size="20" /></td>
			<th>创建时间</th>
			<td><input id="t1" name="sv.createDate" value="" readonly size="20" /></td>
		</tr>
		</table>
</form>		
<br />	
<script type="text/javascript">
	setCurTime('t1');
</script>
</body>
</html>