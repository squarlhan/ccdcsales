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
      <tr align="center"><td><li><s:a href="cycloginchuyunchuAdmin.action">返回库选择页</s:a></li></td></tr>     
      <tr align="center"><td><li><s:a href="cyclistuncheckProducts.action">未入库产品</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="cyclistunentryProducts.action">未到货产品</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="cyclistallstorage!getInfoByDate.action">查看库存</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="cycgoentry.action">产成品入库单</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="cycgocycdelivery.action">装车到储运处</s:a></li></td></tr>
      <!--  <tr align="center"><td><li><s:a href="cycgozckdelivery.action">装车到驻厂库</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="cycgozzkdelivery.action">装车到中转库</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="cycgodelivery.action">装车销售</s:a></li></td></tr>-->
      <tr align="center"><td><li><s:a href="cycsearchdayreport.action">产成品进销存日报</s:a></li></td></tr>
	  <tr align="center"><td><li><s:a href="cycgounqualified.action">不合格产成品处理</s:a></li></td></tr>
	  <tr align="center"><td><li><s:a href="cycgolost.action">产成品损耗处理</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="cycgetxsyiku.action">销售移库</s:a></li></td></tr>
      <tr align="center"><td><li><s:a href="cycgetxsfahuo.action">销售发货</s:a></li></td></tr>   
      <tr align="center"><td><li><s:a href="/Server/logout">退出登陆</s:a></li></td></tr>
    </table>
  </body>
</html>