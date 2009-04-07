<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中转库</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
</head>
<body>
<h2>欢迎进入大成仓储管理信息系统中转库管理页面！</h2>

<h3>请选择仓库:</h3>
 <s:form>
 <table >
 
 <s:iterator id="cangku" value="cangkulist" status="index">
 
  <tr>
 <td width="30%">&nbsp;</td>
 <td align="center" width="30%"> 
                 <a href="<s:url action="zzkselectcangku"><s:param name="cangkuId" value="#cangku.id"/>
                 </s:url>"><s:property value="#cangku.name" />
				 </a></td> 
 
 </tr>
 
 </s:iterator>
 
 
 </table>
 </s:form>
</body>
</html>