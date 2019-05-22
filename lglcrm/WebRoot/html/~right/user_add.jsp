<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>新建用户</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="/lglcrm/js/common.js"></script>
<script type="text/javascript" src="/lglcrm/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function ck_name(ID,infoID)
{
	var str = document.getElementById(ID).value;
	var info = document.getElementById(infoID);
	info.innerHTML = "";
	var reg = /^\S{1,30}$/;
	if(reg.test(str) == false){
		info.innerHTML = "*必填(不含空格,不超30个字符)";
		return false;
	}
		$.post("user_nameCheck",{"name":str},function(data){
			if(data=="yes"){
				document.getElementById("info1").style.color="green";
				document.getElementById("info1").innerHTML="用户名可用！";
				document.getElementById("btn1").disabled=false;
			}else{
				document.getElementById("info1").style.color="red";
				document.getElementById("info1").innerHTML="用户名不可用！";
				document.getElementById("btn1").disabled=true;
			}
		})
}

function check(){
	var name=document.getElementById("c1").value;
	var pwd=document.getElementById("c2").value;
	if(name==""){
		alert("用户名不能为空！");
		return;
	}
	if(pwd==""){
		alert("密码不能为空！");
		return;
	}
	dosubmit("form1");
}
</script>
</head>
<body>

<div class="page_title">用户管理&gt;新建用户</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
	<button class="common_button" onclick="check();" id="btn1">保存</button>  
</div>
<form action="user_add" method="post" id="form1" >
	<table class="query_form_table">
		<tr>
			<th>用户名</th>
			<td><input type="text" value="" name="u.username" onblur="ck_name('c1','info1')" id="c1" /><span id="info1" class="red_star">*</span></td>
		</tr>
		<tr>
			<th>密码</th>
			<td><input type="password" value="" name="u.password" onblur="ck_pwd('c2','info2')" id="c2" /><span id="info2" class="red_star">*</span></td>
		</tr>
		<tr>
			<th>所属角色</th>
			<td>
				<select name="u.role.roleId">
					<s:iterator var="v" value="#session.rolelist">
						<option value="<s:property value='#v.roleId'/>" ><s:property value="#v.roleName" /></option>
					</s:iterator>
				</select>
			</td>
		</tr>
	</table>
</form>
</body>
</html>