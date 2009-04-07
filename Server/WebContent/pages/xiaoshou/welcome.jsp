<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售中心</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
</head>
<body>
<h2>欢迎进入大成仓储管理信息系统销售中心管理页面！</h2>
  
   <table class="list_table" align="center" width="640" id="tb">
		<tr bgcolor="#4A708B">
		    <th>仓库</th>
		    <th>产品</th>
		    <th>规格</th>
		    <th>数目</th>
		    <th>重量</th>
			<th>类型</th>
		</tr>
		<s:iterator id="result" value="resultList" status="index">		
			<tr bgcolor='<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>'>
	           <td align="center">
					<s:property  value="#result.canku.name" /> 
			   </td>
			   <td align="center">
					<s:property  value="#result.product.name" /> 
			   </td>
			   <td align="center">
					<s:property  value="#result.specification.displayName" />
			   </td>
	           <td align="center">
					<s:property  value="#result.number" />
			   </td>
	           <td align="center">
					<s:property  value="#result.weight" />
			   </td>
			   <td align="center">
					<s:property  value="#result.saletype" />
			   </td>
	        </tr>
	        
	    </s:iterator>
</table>


</body>
</html>