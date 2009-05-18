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
    <table align="center" width="100%" id="nav">
      <tr align="center"><td><li><s:a href="/Server/index.jsp">返回主页</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="xsliststorage.action">查看库存</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="xslistsales.action">查看销售记录</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="xsgomoveproduct.action">产品移库确认</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="xslistxsyk.action">删除移库单</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="xsgosalesnotification.action">销售发货通知单</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="xslistxsfh.action">删除发货单</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="/Server/logout">退出登陆</s:a></li></td></tr>
    </table>
  </body>
</html>