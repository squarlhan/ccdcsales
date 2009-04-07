<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="org.springframework.security.context.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息设置</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript"> 
function confirm_update()  
{
	var cf = confirm("确认修改？");
	if(cf)
	{
		var username = (document.getElementById("username")).value;
		var pass = (document.getElementById("newpassword")).value;
		var repass = (document.getElementById("repassword")).value;
		var phone = (document.getElementById("phone")).value;
		if(pass!=repass){
			alert("两次密码输入不一致 ");
			return false;
			}
		
		else return true;
	}
	return false;
}
</script>
</head>
<body>
<div id="rightmain">
	  <h2>个人信息设置页面</h2>
<div id="login">
<s:form  onsubmit="return confirm_update()">
<table width="200" align="center" style="margin-left:30">
	<tr>
	<td align="center"><s:textfield readonly="true" size="15" label="您的用户名" name="username" /></td>
	</tr>
	<tr>
	<td align="center"><s:password size="17" label="请输入密码" name="newpassword" /></td>
	</tr>
	<tr>
	<td align="center"><s:password size="17" label="请确认密码" name="repassword" /></td>
	</tr>
	<tr>
	<td align="center"><s:textfield size="15" label="您的手机号" name="phone" /></td>
	</tr>
	</table>
	<br/>
	<table width="150" align="center">
	<tr>
	<td width="80" align="center"><s:submit value="更改" theme="simple" action="userupdateUser"/>
	</td>
	
	<td width="80" align="center"> <s:reset value="取消"
		theme="simple" /></td></tr></table>
</s:form></div>
</div>

</body>
</html>