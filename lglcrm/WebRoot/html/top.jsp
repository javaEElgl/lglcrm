<%@ page language="java" import="java.util.*,author.pojo.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'top.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>


<body style="border-bottom:solid 1px #666;">
	<form action="user_exit" method="post">
		<TABLE style="width:100%;">
			<TR>
				<td></td>
				<td style="font-family:黑体;font-size:33px;font-weight:bold;">
					客户关系管理系统</td>
				<td width="25%" align="right" style="font-size:12px;"
					valign="bottom">当前用户：${user.username }&nbsp;&nbsp;
					<button>退出系统</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
