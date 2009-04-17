<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>储运处</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language = "javascript" >
	function check()
	{
		var ele = document.getElementById("wer")
		var ele2 = document.getElementById("rew")
		if(ele.value.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
			alert("请输入负责人");
			return false;
		}
		else if(ele2.value.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
			alert("请输入编号");
			return false;
		}
		else if(ele2.value.length>14)
		{
			alert("编号应不超过14位");
			return false;
		}
		if(confirm("确认产品入库？"))
			return true;
		else
			return false;
	}
</script>
</head>
<body>
<h2>到货产品入库</h2>
  <s:form theme="simple" action="cyccheckinproducts" onsubmit="return check()">
    <table class="list_table"  align="center" width="320" id="tb0">
  	<tr bgcolor="#4A708B">
  		<th width="160">负责人</th>
		<th width="160">编号</th>
  	</tr>
  	<tr>
  		<td><s:textfield id="wer" name="rkfzr" labelposition="top"/></td>
       <td><s:textfield id="rew" name="bno" labelposition="top"/></td>
  	</tr>
  </table>
  <br/><br/><br/>
   <table class="list_table"  align="center" width="640" id="tb">
		<tr bgcolor="#4A708B">
		    <th width="5%">选择</th>
		    <th width="15%">编号</th>
		    <th width="10%">产品名</th>
		    <th width="15%">批号</th>
		    <th width="10%">规格</th>
			<th width="10%" style="display:none">数量</th>
			<th width="15%">重量(T)</th>
			
		</tr>
		<s:iterator id="result" value="resultList" status="index">		
			<tr bgcolor='<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>'>
			   <td align="center">
	               <s:checkbox name="checked[%{#index.index}]"></s:checkbox>
	           </td>
	           <td align="center">
					<s:textfield size="10" readonly="true" name="resultList[%{#index.index}].Id" value="%{#result.Id}" /> 
			   </td>
			   <td align="center">
					<s:textfield size="10" readonly="true" name="resultList[%{#index.index}].products.name" value="%{#result.products.name}" />
			   </td>
	           <td align="center">
					<s:textfield size="10" readonly="true" name="resultList[%{#index.index}].pch" value="%{#result.pch}" />
			   </td>
	           <td align="center">
					<s:textfield size="10" readonly="true" name="resultList[%{#index.index}].specifications.name" value="%{#result.specifications.displayName}" />
			   </td>
			   <td align="center" style="display:none">
					<s:textfield size="10" name="resultList[%{#index.index}].number" value="%{#result.number}" /> 
			   </td>
			   <td align="center">
			          <s:textfield size="10" readonly="true" value="%{#result.number*#result.specifications.weight}"/>
			   </td>
	        </tr>
	        
	    </s:iterator>
	    </table>
		<table align="center">
	    <tr>
				<td align="center">
					<s:submit disabled="flag" value="产品入库" />
				</td>
				<td align="center">
					<s:reset value="重置" />
				</td>
			</tr></table>
	    
	     
  </s:form>

</body>
</html>