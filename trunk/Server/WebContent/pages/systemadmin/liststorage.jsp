<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>库存</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript">
	function print(){
		document.myform.action = "adminprintwarehouse.action";
		return true;
	}
</script>
<s:head/>
</head>
<body>
<h2>库存状态</h2>


<s:form id="myform" theme="simple">
  <table align="center" width="80%" border="0" class="list_table"> 
 <tr>
    <td><s:text name="选择时间:"/></td>      
	<td><s:datetimepicker theme="simple" name="date" label="选择报表日期" toggleType="explode"/></td>      
	<td><s:text name="选择仓库:"/></td>
	<td><s:select  theme="simple" name="canku" label="选择仓库名" labelposition="left" multiple="false" 
            list="cangkusList" listKey="id" listValue="name"/>
    </td>
  
    <td align="left">
		  <s:submit value="查看" theme="simple" action="adminliststorage!execute1"/></td>
	<td><input value="打印库存单"  type="submit" onclick="return print()"/></td>
 </tr>
</table>

 <table class="list_table" align="center" border="1" width="100%">
 <tr bgcolor="#4A708B">
     <th>产品名称</th>
     <th>入库量(T)</th>
     <th>出库量(T)</th>
     <th>工厂库存(T)</th>
 </tr>
 <s:iterator id="pr" value="resultList" status="index"> 
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

   <table class="list_table" align="center" border="1" width="100%" >
  <tr bgcolor="#4A708B">
    <th width="25%">产品名称</th>
    <th>内销</th>
    <th>外销</th>
    <th>待检</th>
    <th>定向</th>
    <th>不合格</th>
   </tr>
   <s:iterator id="pr_memo" value="resultList" status="index"> 
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