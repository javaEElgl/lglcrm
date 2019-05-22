<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改联系人</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
<script type="text/javascript">
function check(){
	if(ck_null('c1','info1')&&ck_null('c2','info2')&&ck_phone('c3','info3')){
		dosubmit('form1');
	}
}
</script>
</head>
<body>

<div class="page_title">客户信息管理 &gt;客户信息&gt;联系人&gt;修改联系人</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
	<button class="common_button" onclick="check();">保存</button>  
</div>
<form action="linkman_update" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>姓名</th>
			<td>
				<input name="l.ID" value="${request.editlinkman.ID}" readonly style="display:none" />
				<input name="l.name" value="${request.editlinkman.name}" onblur="ck_null('c1','info1')" id="c1"/><span id="info1" class="red_star">*</span></td>
			<th>性别</th>
			<td>
				<select name="l.sex">
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>职位</th>
			<td><input name="l.postion" value="${request.editlinkman.postion}" onblur="ck_null('c2','info2')" id="c2"/><span id="info2" class="red_star">*</span></td>
			<th>办公电话</th>
			<td><input name="l.tel" value="${request.editlinkman.tel}" /></td>
		</tr>	
		<tr>
			<th>手机</th>
			<td><input name="l.phone" value="${request.editlinkman.phone}" size="20" onblur="ck_phone('c3','info3')" id="c3"/><span id="info3" class="red_star">*</span></td>
			<th>备注</th>
			<td><input name="l.memo" value="${request.editlinkman.memo}" size="20" /></td>
		</tr>
	</table>
</form>
</body>
</html>