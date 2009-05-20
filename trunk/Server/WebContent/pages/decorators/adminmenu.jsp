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
      <tr align="center"><td><li><s:a href="adminliststorage.action">库存查看</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="adminlistonwayProducts.action">在途产品查看</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="adminlistsales.action">销售查看</s:a></li></td></tr>     
      <tr align="center"><td><li><s:a href="adminlistdestroy.action">销毁查看</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="adminlistloss.action">损耗查看</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="adminproductsmanager.action">产品信息管理</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="admincanmanager.action">仓库信息管理</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="adminspemanager.action">规格信息管理</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="adminusermanager.action">用户信息管理</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="admincustommanager.action">客户信息管理</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="admingroupmanager.action">组信息管理</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="adminfahuomanager.action">发货单位信息管理</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="admingetallgroups.action">组员设置管理</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="admingroupprimanager.action">组权限分配管理</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="adminuserprimanager.action">用户权限分配管理</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="admincfrmanager.action">储运处与工厂对应关系管理</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="/Server/logout">退出登陆</s:a></td></tr>
    </table>
  </body>
</html>