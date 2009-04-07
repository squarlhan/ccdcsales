<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><decorator:title default="大成集团" /></title>

<link href="/Server/css/main.css" rel="stylesheet" type="text/css" />
<link href="/Server/css/css.css" rel="stylesheet" type="text/css" />
<decorator:head />
</head>

<body>
<div id="maincontent">
<table align="center" width="800">
	<tr>
		<td align="left" id="header"
			height="100">
		</td>
	</tr>
</table>
<table align="center" width="800">
	<tr>
		<td valign="top">
		<div id="leftmenu">
		<page:applyDecorator page="/pages/decorators/zckmenu.jsp" name="panel" /> 
		<page:applyDecorator page="/pages/decorators/link.jsp" name="panel" />
		</div>
		</td>
		<td width="100%">
		<table width="100%" height="100%">
			<tr>
				<td id="pageTitle"><!--decorator:title/--></td>
			</tr>
			<tr>
				<td valign="top" height="100%"><div id="rightmain"> <decorator:body/></div></td>
			</tr>

		</table>
		</td>
	</tr>

</table>
<table align="center" width="800">
	<tr>
		<td id="footer" background="/Server/images/logo08.gif">
		<div id="footer">
		   <p>主办单位:大成集团库管信息系统</p>
		   <p>COPYRIGHT&copy;2009 dacheng Group ALL RIGHT RESERVED</p>
		</div>
		<div id="banner"></div>
		</td>
	</tr>
</table>
</div>
</body>

</html>