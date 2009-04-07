<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产成品进销存（发运）日报</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
</head>

<body>
<table class="list_table"  align="center" width="100%">
  <tr align="center">
     <td><h2>产成品进销存（发运）日报</h2></td>
  </tr>
 </table>
  <s:form>
 
 <table class="list_table"  align="center" border="1" width="800">
 <tr>
     <th>产品名称</th>
     <th>入库量(T)</th>
     <th>日销量(T)</th>
     <th>工厂库存(T)</th>
 </tr>
 <s:iterator id="pr" value="reportpmxlist" status="index"> 
    <tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
         <td align="center">
			   <s:property value="#pr.prdid.name" />
		 </td>
		 <td align="center">
			   <s:property value="#pr.rkt" />
		 </td>
		 <td align="center">
			   <s:property value="#pr.ckt" />
		 </td>
		 <td align="center">
			   <s:property value="#pr.kct" />
		 </td>
     </tr>
         
</s:iterator> 
 </table>
 <table class="list_table"  align="center" border="1" width="800">
 <tr>
   <th width="25%">产品名称</th>
   <th>去向</th>
   <th>重量</th>
 </tr>
 <s:iterator id="pr_qux" value="reportcmxlist" status="index"> 
    <tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
         <td align="center">
			   <s:property value="#pr_qux.prdid" />
		 </td>
		 <td align="center">
			   <s:property value="#pr_qux.ckid" />
		 </td>
		 <td align="center">
			   <s:property value="#pr_qux.ckt" />
		 </td>
		 </tr>
		 </s:iterator>
 </table>
  <table class="list_table"  align="center" border="1" width="800" >
  <tr>
    <th width="25%">产品名称</th>
    <th>内销</th>
    <th>外销</th>
    <th>待检</th>
    <th>定向</th>
   </tr>
   <s:iterator id="pr_memo" value="reportpmxlist" status="index"> 
    <tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
         <td align="center">
			   <s:property value="#pr_memo.prdid.name" />
		 </td>
		 <td align="center">
			   <s:property value="#pr_memo.nxt" />
		 </td>
		  <td align="center">
			   <s:property value="#pr_memo.wxt" />
		 </td>
		  <td align="center">
			   <s:property value="#pr_memo.djt" />
		 </td>
		  <td align="center">
			   <s:property value="#pr_memo.dxt" />
		 </td>
    </tr>
    </s:iterator>
  </table>
 </s:form>
</body>
</html>
