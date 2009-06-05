<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import = "java.util.*" %>
<%@page import = "java.text.SimpleDateFormat" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>液体产成品进销存（发运）日报</title>
<s:head/>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
</head>
<body>
<h2>液体产成品进销存（发运）日报</h2>
<s:form name="formname" label="label" action="cycsearchdayreport_yeti" theme="simple">
<table class="list_table"  align="center" border="0" width="100%">
 <tr>
    <td><s:text name="起始日期:"/></td>
    <td align="left" width="25%"><s:datetimepicker name="begindate" label="选择日期" toggleType="explode" theme="simple"/></td>
     <td><s:text name="截止日期:"/></td>
    <td align="left" width="25%"><s:datetimepicker name="enddate" label="选择日期" toggleType="explode" endDate="today" theme="simple"/></td>
    <td align="center"><s:submit value="提交"/></td>
</tr>
</table>
<table class="list_table"  align="center" border="1" width="100%">
 <tr bgcolor="#4A708B">
     <th>产品名称</th>
     <th>入库量(T)</th>
     <th>车间用(T)</th>
     <th>出库量(T)</th>
     <th>库存量(T)</th>
     <th>产量(T)</th>
     <th>灌区量(T)</th>
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
			   <s:property value="#pr.cjt" />
		 </td>
		 <td align="center">
			   <s:property value="#pr.ckt" />
		 </td>
		 <td align="center">
			   <s:property value="#pr.kct" />
		 </td>
		 <td align="center">
			   <s:property value="#pr.ct" />
		 </td>
		 <td align="center">
			   <s:property value="#pr.gqt" />
		 </td>
     </tr>
</s:iterator> 
 </table>
 <table class="list_table"  align="center" border="1" width="100%">
 <tr bgcolor="#4A708B">
   <th width="25%">产品名称</th>
   <th>去向</th>
   <th>重量(T)</th>
   <th>备注</th>
 </tr>
 <s:iterator id="pr_qux" value="reportcmxlist" status="index"> 
    <tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
         <td align="center">
			   <s:property value="#pr_qux.prdid.name" />
		 </td>
		 <td align="center">
			   <s:property value="#pr_qux.rkid.name" />
		 </td>
		 <td align="center">
			   <s:property value="#pr_qux.ckt" />
		 </td>
		  <td align="center">
			   <s:property value="#pr_qux.memo" />
		 </td>
		 </tr>
		 </s:iterator>
 </table>
  <table class="list_table"  align="center" border="1" width="100%" >
  <tr bgcolor="#4A708B">
    <th width="25%">产品名称</th>
    <th>内销</th>
    <th>外销</th>
    <th>待检</th>
    <th>定向</th>
    <th>不合格</th>
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
		  <td align="center">
			   <s:property value="#pr_memo.bhgt" />
		 </td>
    </tr>
    </s:iterator>
  </table>
 </s:form>
</body>
</html>
