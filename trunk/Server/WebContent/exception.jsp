<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>异常页面</title>

<link href="/Server/css/main.css" rel="stylesheet" type="text/css" />
<link href="/Server/css/css.css" rel="stylesheet" type="text/css" />
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
		<td width="100%">
		<table width="100%" height="100%">
			<tr>
				<td id="pageTitle"></td>
			</tr>
			<tr>
				<td valign="top" height="100%">
				  <div id="rightmain">
				    <table align="center">
	                  <tr><td class="STYLE5">错误信息如下：</td></tr>
                      <tr><td class="STYLE5"><s:property value="exception.message"/></td></tr>
                      <tr><td class="STYLE5">错误详细信息如下：</td></tr>
                      <tr><td class="STYLE5"><s:property value="exceptionStack"/></td></tr>
                    </table>
                  </div>
                </td>
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