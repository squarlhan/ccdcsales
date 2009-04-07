<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>储运处入库信息</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
</head>
<body>
<h2>储运处入库信息</h2>
<table class="list_table"  align="center" width="100%">
<tr align="center"><td>
  <s:form action="cycallentrywarehouse">
     <table class="list_table"  align="center" width="100%">
		<tr bgcolor="#4A708B">
		    <th width="25%">批号</th>
		    <th width="10%">产品号</th>
		    <th width="10%">规格</th>
		    <th width="10%">数目</th>
			<th width="10%">销售类型</th>
			<th width="10%">待检/合格</th>
			<th width="25%">备注</th>
		</tr>
		<s:iterator id="result" value="#session.temprumxs" status="index">		
			<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
	           <td align="center">
					<s:property value="#result.pch" />
			   </td>
			   <td align="center">
					<s:property value="#result.products.id" />
			   </td>
			   <td align="center">
					<s:property value="#result.specifications.id" />
			   </td>
			   <td align="center">
					<s:property value="#result.number" />
			   </td>
			   <td align="center">
					<s:property value="#result.saleType" />
			   </td>
			   <td align="center">
					<s:property value="#result.status" />
			   </td>
			   <td align="center">
					<s:property value="#result.memo" />
			   </td>
	        </tr>
	        
	    </s:iterator>
	    </table>
	    <tr align="center">
       <td><s:select name="canku" label="请选择仓库" labelposition="top" multiple="false"
            list="cankusList" listValue="name" listKey="id" headerValue="--请选择 --"/></td>
       <td><s:textfield label="请填写操作员" name="rkczy" labelposition="top"/></td>
       <td><s:textfield label="请填写负责人" name="rkfzr" labelposition="top"/></td>
       <td><s:textfield label="请填写编号" name="bno" labelposition="top"/></td>
       <td><s:submit value="入库" align="left"/></td>
		</tr>
  </s:form>
  </td></tr>
  </table>
</body>
</html>