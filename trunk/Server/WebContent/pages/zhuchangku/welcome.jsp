<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>驻厂库</title>
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
<h2>欢迎进入大成仓储管理信息系统驻厂库管理页面！</h2>
  <s:form theme="simple" action="zckcheckinproducts" onsubmit="return check()">
    <table class="list_table" align="left" width="320" id="tb0">
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
   <br/><br/>
  <br/>
   <table class="list_table" align="center" width="640" id="tb">
		<tr bgcolor="#4A708B">
		    <th width="5%">选择</th>
		    <th width="15%">编号</th>
		    <th width="10%">产品名</th>
		    <th width="15%">批号</th>
		    <th width="10%">规格</th>
			<th width="10%">数量</th>
			
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
			   <td align="center">
					<s:textfield size="10" name="resultList[%{#index.index}].number" value="%{#result.number}" /> 
			   </td>
	        </tr>
	        
	    </s:iterator>
	    <tr>
				<td align="center">
					<s:submit disabled="flag" value="检查入库" />
				</td>
				<td align="center">
					<s:reset value="重置" />
				</td>
			</tr>
	    </table>
	     
  </s:form>

</body>
</html>