<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/style.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<div class="action">
		<div class="t" style="font-size:20px">提示信息：</div>
		<div class="pages">
		<br>
		<br>
		    <table width="90%" border="0" cellspacing="0" cellpadding="0">
			 	 <tr align="center">
			 	 	<td>
			 	 		<font color="red" size="5" > <s:property value="#request.error_message" /></font>
			 	 	</td>
			 	 </tr>
			</table>  
		</div>
	</div>
</body>
</html>


