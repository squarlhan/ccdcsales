<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="org.springframework.security.context.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大成登陆页面</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
</head>
<body>
<div id="rightmain">
	  <h2>大成登陆页面</h2>
<div id="login">
<s:form action="j_spring_security_check">
<table width="200" align="center" style="margin-left:30">
	<tr>
	<td align="center"><s:textfield size="15" label="用户名" name="j_username" /></td>
	</tr>
	<tr>
	<td align="center"><s:password size="17" label="密码" name="j_password" /></td>
	</tr>
	</table>
	<br/>
	<table width="150" align="center">
	<tr>
	<td width="80" align="center"><s:submit value="登陆" theme="simple" />
	</td>
	
	<td width="80" align="center"> <s:reset value="取消"
		theme="simple" /></td></tr></table>
</s:form></div>
</div>

</body>
</html>