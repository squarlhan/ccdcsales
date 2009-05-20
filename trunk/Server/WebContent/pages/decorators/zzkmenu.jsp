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
      <tr align="center"><td><li><s:a href="zzkloginAdmin.action">返回库选择页</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="zzklistuncheckProducts.action">未到货产品</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="zzklistallstorage!getInfoByDate.action">查看库存</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="zzklistonwayProducts.action">在途产品查看</s:a></li></td></tr>
      <!--  <tr align="center"><td><li><s:a href="zzkgodelivery.action">移库</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="zzkgosaledelivery.action">销售</s:a></li></td></tr>-->
      <tr align="center"><td><li><s:a href="zzkgetreport.action">日报单</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="zzkgolost.action">产成品损耗处理</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="zzkgetxsyiku.action">销售移库</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="zzkgetxsfahuo.action">销售发货</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="/Server/logout">退出登陆</s:a></li></td></tr>
      

    </table>
  </body>
</html>