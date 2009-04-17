<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>驻厂库的库存</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<s:head/>
</head>
<body>
<h2>驻厂库的库存状态</h2>
 <s:form>
<table class="list_table" align="center" width="100%" >
 <tr>      
    <td width=""><s:datetimepicker name="mydate" label="选择报表日期" toggleType="explode" value="" theme="simple"/></td>      
    <td width="">
			<s:submit value="查库存" theme="simple" action="zcklistallstorage!getInfoByDate"/>
			<s:submit value="查入库单" theme="simple" action="zckcheckin!getInfoByDate"/>
			<s:submit value="查出库单" theme="simple" action="zckcheckout!getInfoByDate"/>
	</td>
 </tr>
 </table>
<table class="list_table" align="center" width="100%" >
<tr align="center"><td>
  <s:form theme="simple" action="zcklistuncheckProducts">
     <table class="list_table" align="center" width="100%">
		<tr bgcolor="#4A708B">
		    <th width="20%">批号</th>
		    <th width="10%">产品名</th>
		    <th width="10%">规格</th>
		    <th width="10%">重量(T)</th>
			<th width="20%">去向</th>
		</tr>
		<s:iterator id="result" value="resultList" status="index">		
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
					<s:property value="#result.number*#result.specifications.weight" />
			   </td>
			   <td align="center">
					<s:property value="#result.rkid.name" />
			   </td>
	        </tr>
	        
	    </s:iterator>
	</table>
  </s:form>
  </td></tr>
  </table>
 </s:form>
</body>
</html>