<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>功能菜单</title>
    <link href="/Server/css/main.css" rel="stylesheet" type="text/css" />
    <link href="/Server/css/css.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <table align="left" width="100%" id="nav">
      <tr align="left"><td><li><s:a href="/Server/index.jsp">返回主页</s:a></li></td></tr>
      <tr align="left"><td><li><s:a href="cycloginchuyunchuAdmin.action">储运处功能</s:a></li></td></tr>
      <tr align="left"><td><li><s:a href="zckloginAdmin.action">驻厂库功能</s:a></li></td></tr>
      <tr align="left"><td><li><s:a href="zzkloginAdmin.action">中转库功能</s:a></li></td></tr>
      <tr align="left"><td><li><s:a href="xslogin.action">销售中心功能</s:a></li></td></tr>
      <tr align="left"><td><li><s:a href="adminloginsystemAdmin.action">管理员功能</s:a></li></td></tr>
      <tr align="left"><td><li><s:a href="usergoUser.action">用户设置</s:a></li></td></tr>
      <tr align="left"><td><li><s:a href="usergosmmdy.action">短信订阅</s:a></li></td></tr>
      <tr align="left"><td><li><s:a href="/Server/logout">退出登陆</s:a></li></td></tr>
    </table>
  </body>
</html>