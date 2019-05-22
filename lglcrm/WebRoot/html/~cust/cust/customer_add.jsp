<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>新建客户信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
<script type="text/javascript">
function check(){
	if(ck_null('c1','info1')&&ck_null('c2','info2')&&ck_null('c3','info3')&&ck_null('c4','info4')&&ck_null('c5','info5')&&
	   ck_null('c6','info6')&&ck_null('c7','info7')&&ck_null('c8','info8')&&ck_null('c9','info9')&&ck_null('c10','info10')
	   &&ck_number('c11','info11')&&ck_number('c12','info12')&&ck_number('c13','info13')){
		dosubmit('form1');
	}
}
</script>
</head>
<body>

<div class="page_title">客户信息管理 &gt;新建客户信息</div>
<div class="button_bar">
	<button class="common_button" onclick="back();">返回</button>
	<button class="common_button" onclick="check();">保存</button>
</div>
<form action="customer_add" method="post" id="form1">
	<table class="query_form_table">
		<tr>
			<th>客户编号</th>
			<td><input name="c.no" value="" onblur="ck_null('c1','info1')" id="c1"/><span id="info1" class="red_star">*</span></td>
			<th>名称</th>
			<td><input name="c.name" value="" onblur="ck_null('c2','info2')" id="c2"/><span id="info2" class="red_star">*</span></td>
		</tr>
		<tr>
			<th>地区</th>
			<td>
				<select name="c.region" onblur="ck_null('c3','info3')" id="c3">
					<option value="">请选择...</option>
					<s:iterator var="v" value="#session.dictlist">
						<s:if test='#v.type=="地区"'>
						<option value="<s:property value='#v.item'/>"><s:property value='#v.item'/></option>
						</s:if>
					</s:iterator>
				</select>
				<span id="info3" class="red_star">*</span></td>
			<th>客户经理</th>
			<td>
				<select name="c.manager" onblur="ck_null('c4','info4')" id="c4">
					<option value="">请选择...</option>
					<s:iterator var="v" value="#session.userlist">
						<option value="<s:property value='#v.userName' />"><s:property value='#v.userName' /></option>
					</s:iterator>
				</select>
				<span id="info4" class="red_star">*</span>
			</td>
		</tr>	
		<tr>
			<th>客户等级</th>
			<td>
				<select name="c.level" onblur="ck_null('c5','info5')" id="c5">
					<option value="">请选择...</option>
					<s:iterator var="v" value="#session.dictlist">
						<s:if test='#v.type=="客户等级"'>
						<option value="<s:property value='#v.item' />"><s:property value='#v.item' /></option>
						</s:if>
					</s:iterator>
				</select>
				<span id="info5" class="red_star">*</span>
			</td>
			<th>　</th>
			<td>　</td>
		</tr>
		<tr>
			<th>客户满意度</th>
			<td>
				<select name="c.satisfy">
						<option value="1">☆</option>
						<option value="2">☆☆</option>
						<option value="3">☆☆☆</option>
						<option value="4">☆☆☆☆</option>
						<option value="5">☆☆☆☆☆</option>
				</select>
			</td>
			<th>客户信用度</th>
			<td>
				<select name="c.credit">
					<option value="1">☆</option>
					<option value="2">☆☆</option>
					<option value="3">☆☆☆</option>
					<option value="4">☆☆☆☆</option>
					<option value="5">☆☆☆☆☆</option>
				</select>
			</td>
		</tr>
	</table>
<br />
	<table class="query_form_table" id="table1">
		<tr>
			<th>地址</th>
			<td><input name="c.addr" value="" size="20" onblur="ck_null('c6','info6')" id="c6"/><span id="info6" class="red_star">*</span>
			</td>
			<th>邮政编码</th>
			<td><input name="c.zip" value="" size="20" onblur="ck_null('c7','info7')" id="c7"/><span id="info7" class="red_star">*</span></td>
		</tr>
		<tr>
			<th>电话</th>
			<td><input name="c.tel" value=""  size="20"  onblur="ck_null('c8','info8')" id="c8"/><span id="info8" class="red_star">*</span></td>
			<th>传真</th>
			<td><input name="c.fax" value=""  size="20"  onblur="ck_null('c9','info9')" id="c9"/><span id="info9" class="red_star">*</span></td>
		</tr>	
		<tr>
			<th>网址</th>
			<td><input name="c.website" value="" size="20"  /></td>
			<th>　</th>
			<td>　</td>
		</tr>
	</table>
<br />
	<table class="query_form_table" id="table2">
		<tr>
			<th>营业执照注册号</th>
			<td><input name="c.licenceID" value="" size="20" /></td>
			<th>法人</th>
			<td><input name="c.chieftain" value="" size="20" />
			</td>
		</tr>
		<tr>
			<th>注册资金（万元）</th>
			<td>
				<input name="c.bankroll" value="" size="20" onblur="ck_number('c12','info12')" id="c12"/><span id="info12" class="red_star">*</span></td>
			<th>年营业额（万元）</th>
			<td>
				<input name="c.turnover" value=""  size="20" onblur="ck_number('c13','info13')" id="c13"/><span id="info13" class="red_star">*</span>
			</td>
		</tr>	
		<tr>
			<th>开户银行</th>
			<td>
				<input name="c.bank" value="" size="20" onblur="ck_null('c10','info10')" id="c10"/><span id="info10" class="red_star">*</span>
			</td>
			<th>银行帐号</th>
			<td><input name="c.bankID" value=""  size="20" onblur="ck_number('c11','info11')" id="c11"/><span id="info11" class="red_star">*</span></td>
		</tr>
		<tr>
			<th>地税登记号</th>
			<td>
				<input name="c.localID" value=""  size="20" /></td>
			<th>国税登记号</th>
			<td><input name="c.nationalID" value=""  size="20" /></td>
		</tr>
	</table>
</form>
</body>
</html>