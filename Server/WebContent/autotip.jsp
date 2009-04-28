<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String context = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'AutoTip.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%= context%>/js/lib/prototype.js"></script>
    <script type="text/javascript" src="<%= context%>/js/lib/autocomplete.js"></script>
    <link rel="stylesheet" type="text/css" href="<%= context%>/autocomplete.css" /> 
    <style>
        * {
			font:12px "Segoe UI", Tahoma;	
        }
		h3 {  
			font-size:16px;
			font-weight:bold;
		}
    </style>
  </head>
  
  <body>
    <h3>客户的AjaxComplete</h3>
	<table>
	<tr>
	<td>隐藏值</td>
	<td><input name="provinceId" type="text" id="provinceId" size="24"/></td>
	</tr>
	<tr>
	<td>客户</td>
	<td><input type="text" name="province" size="24"/></td>
	</tr>
	</table>
	
	<script type="text/javascript">
	    //只要一句话就可以完成自动完成，提高开发速度，count表示一次显示的记录条数，显示记录的时候，超过10条显示下拉框
	    new AutoTip.AutoComplete("province", function() {
	        return "<%= context%>/autocomplete.do?method=province&count=10&inputValue=" + escape(this.text.value);
	    });
	
	</script>
	
	<h3>客户的AjaxComplete</h3>
	<form>
	<table>
	<tr>
	<td>客户</td>
	<td><input type="text" name="city" size="24"/></td>
	</tr>
	</table>
	</form>
	<script type="text/javascript">
	   
	    new AutoTip.AutoComplete("city", function() {
	        return "<%= context%>/getcustomers.action?start=" + escape(this.text.value);
	    });
	
	</script>
  </body>
</html>
