<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String context = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'AutoTip.jsp' starting page</title>
    
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
    <h3>�ͻ���AjaxComplete</h3>
	<table>
	<tr>
	<td>����ֵ</td>
	<td><input name="provinceId" type="text" id="provinceId" size="24"/></td>
	</tr>
	<tr>
	<td>�ͻ�</td>
	<td><input type="text" name="province" size="24"/></td>
	</tr>
	</table>
	
	<script type="text/javascript">
	    //ֻҪһ�仰�Ϳ�������Զ���ɣ���߿����ٶȣ�count��ʾһ����ʾ�ļ�¼��������ʾ��¼��ʱ�򣬳���10����ʾ������
	    new AutoTip.AutoComplete("province", function() {
	        return "<%= context%>/autocomplete.do?method=province&count=10&inputValue=" + escape(this.text.value);
	    });
	
	</script>
	
	<h3>�ͻ���AjaxComplete</h3>
	<form>
	<table>
	<tr>
	<td>�ͻ�</td>
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
