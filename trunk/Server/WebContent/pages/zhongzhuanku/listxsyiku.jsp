<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售移库</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<s:head/>
</head>
<body>
<h2>销售移库</h2>
<s:form theme="simple" action="zzkgoxsyiku.action">
  
     <table class="list_table" align="center" width="100%">
		<tr bgcolor="#4A708B">
		    <th width="25%">产品名</th>
		    <th width="15%">重量(T)</th>
		    <th width="15%">规格</th>
		    <th width="15%">操作</th>
		</tr>
		<s:iterator id="result" value="xsyklist" status="index">		
			<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
	           <td align="center">
					<s:property value="#result.product.name" />
			   </td>
			  <td align="center">
					<s:property value="#result.number*#result.specification.weight" />
			   </td>
			   <td align="center">
					<s:property value="#result.specification.displayName" />
			   </td>
			   <td align="center">
			   		<a href="<s:url action="zzkgoxsyiku"><s:param name="index" value="%{#result.id}"/></s:url>">出库</a>
			   </td>
	        </tr>
	    </s:iterator>
	</table>
 </s:form>
</body>
</html>