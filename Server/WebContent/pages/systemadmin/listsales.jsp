<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售总量及明细</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<s:head/>
</head>

<body>
<table align="center" width="100%">
  <tr align="center">
     <td><h2>销售总量及明细</h2></td>
  </tr>
 </table>
<s:form action="adminlistsales!listBetween.action">
	<table class="list_table" align="center" width="100%">
	  <tr align="center">
	  	<td width="20%">起始时间</td>
	  	<td width="20%"><s:datetimepicker name="begindate" label="1" toggleType="explode" value="today" theme="simple"/></td>
	  	<td width="20%">截止时间</td>
	  	<td width="20%"><s:datetimepicker name="enddate" label="2" labelposition="left" toggleType="explode" value="today" theme="simple"/></td>
	     <td width="20%"><s:submit value="查看销售详情" theme="simple"/></td>
	  </tr>
	 </table>
 	<table class="list_table" align="center" border="1" width="100%">
	 <tr bgcolor="#4A708B">
	   <th width="25%">产品名称</th>
	   <th width="25%">重量(T)</th>
	 </tr>
	 	<s:iterator id="pr_qux" value="cmxList" status="index"> 
	    <tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
	         <td align="center">
				   <s:property value="#pr_qux.prdid.name" />
			 </td>
			 <td align="center">
				   <s:property value="#pr_qux.ckt" />
			 </td>
			 </tr>
			 </s:iterator>
	 </table>
	 
 <table class="list_table" align="center" width="100%">
		<tr bgcolor="#4A708B">
		    <th width="20%">批号</th>
		    <th width="10%">产品名</th>
		    <th width="10%">规格</th>
		    <th width="10%">数目</th>
			<th width="20%">去向</th>
		</tr>
		<s:iterator id="result" value="listchukumx" status="index">		
			<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
	           <td align="center">
					<s:property value="#result.pch" />
			   </td>
			   <td align="center">
					<s:property value="#result.products.name" />
			   </td>
			   <td align="center">
					<s:property value="#result.specifications.displayName" />
			   </td>
			   <td align="center">
					<s:property value="#result.number" />
			   </td>
			   <td align="center">
					<s:property value="#result.chuku.custom.customName" />
			   </td>
	        </tr>
	    </s:iterator>
	</table>
	
 </s:form>
</body>
</html>
