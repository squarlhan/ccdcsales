<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>驻厂库登陆</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
</head>
<body>
<table align="center" width="100%">
  <tr align="center">
     <td><h2>驻厂库登陆页面</h2></td>
  </tr>
   <tr align="center"><td>
  <s:form action="zckloginAdmin">
     <tr align="center">
       <td><s:textfield label="用户名" name="username"/></td>
     </tr>
     <tr>
       <td><s:password  label="密码" name="password"/></td>
     </tr>
     <tr align="right" >
      <td>
       <s:submit value="登陆" theme="simple"/>
       <s:reset value="取消" theme="simple"/>
      </td>
     </tr>
  </s:form>
  </td></tr>
 </table>
</body>
</html>