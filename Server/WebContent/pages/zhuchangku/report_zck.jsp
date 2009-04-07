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
<script type="text/javascript">
function checkNull()
{
	var ele = document.getElementById("bno");
	if(ele.value.replace(/(^\s*)|(\s*$)/g,"") == "")
	{
	  alert("请输入编号!");
	  return false;
	}
	if(ele.value.length>12)
	{
	  alert("编号应不超过12位!");
	  return false;
	}
	if(confirm("确认提交？"))
		return true;
	else
		return false;
}
</script>
</head>

<body>
<table align="center" width="100%">
  <tr align="center">
     <td><h2>产成品进销存（发运）日报</h2></td>
  </tr>
 </table>
<s:form action="zcksavereport.action"  onsubmit="return checkNull()">
<table class="list_table" align="center" border="0" width="100%">
 <tr>
     <td align="left"><s:text name="编号:"/></td>
     <td align="left"><s:textfield name="bno" theme="simple"/></td>
     <td align="left"><s:text name="日期:"/></td>
     <td align="left"><s:date name="mydate" format="yyyy-MM-dd"/></td>
      <td align="left"><s:submit value="提交" theme="simple"/></td>
</tr>
 </table>

 <table class="list_table" align="center" border="1" width="100%">
 <tr bgcolor="#4A708B">
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
 <table class="list_table" align="center" border="1" width="100%">
 <tr bgcolor="#4A708B">
   <th width="25%">产品名称</th>
   <th>去向</th>
   <th>重量(T)</th>
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
		 </tr>
		 </s:iterator>
 </table>
 </s:form>
</body>
</html>
