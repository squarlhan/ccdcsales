<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统管理员登陆</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
</head>
<body>
  <s:form action="adminloginsystemAdmin.action" >
  <table align="center">
	  <tr >
		  <td align="center" >
	     	<h2 >系统管理员登陆页面</h2>
	     </td>
	  </tr>
	  <tr>
	  		<td align="center">
	  		<label>用户名：</label>
	  		<s:textfield  name="username"/>
	  		</td>
	  </tr>
	  <tr>
	  	  <td align="center">
	  	  	<label>密码：</label>
	      	<s:password  name="password"/>
	      </td>
	  </tr>
	  <tr>
	  	<td align="center">
	      <s:submit value="登陆" theme="simple"/>
	      <s:reset value="取消" theme="simple"/>
	     </td>
	  </tr>
  </table>
  </s:form>
</body>
</html>