<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>储运处</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language = "javascript" >
	function confirmbtn()
	{
		var cf = confirm("确认入库？");
		if(cf)
			return true;
		else
			return false;
	}

	function confirm_delete(rownum)
	{
		var cf = confirm("确认删除？");
		if(cf)
		{
			var cid = (document.getElementById("cid_"+rownum)).value;
			urlpara_delete(cid);
		}
	}


	function urlpara_delete(cid) 
	{
	    location.href= encodeURI("cyccheckinwarehouse!delete.action?id="+cid); 
	} 
	
</script>
</head>
<body>
<h2>欢迎进入大成仓储管理信息系统储运处管理页面！</h2>
<table class="list_table"  align="center" width="640">
  <s:form theme="simple" action="cyccheckinwarehouse" onsubmit="return confirmbtn()">
		<tr bgcolor="#4A708B">
		    <th>编号</th>
		    <th>产品名</th>
		    <th>批号</th>
			<th>销售类型</th>
			<th>是否合格</th>
		    <th>操作</th>
		</tr>
		<s:iterator id="result" value="resultList" status="index">		
			<tr bgcolor='<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>'>
	           <td align="center">
					<s:textfield readonly="true"  id="cid_%{#index.index}" name="resultList[%{#index.index}].Id" value="%{#result.Id}" size="4"/> 
			   </td>
			   <td align="center">
					<s:property value="#result.products.name" />
			   </td>
	           <td align="center">
					<s:property value="#result.pch" />
			   </td>
	           <td align="center">
					<s:select name="resultList[%{#index.index}].saleType" multiple="false" list="#{'0':'请选择','1':'内销','2':'定向','3':'外销'}" listKey="key" listValue="value"/>
			   </td>
			   <td align="center">
					<s:select name="resultList[%{#index.index}].status" multiple="false" list="#{'0':'待检','1':'合格','2':'不合格'}" listKey="key" listValue="value"/>
			   </td>
			   <td align="center">
			   <a href="javascript:confirm_delete(${index.index})">删除</a> 
			   </td>
	        </tr>
	    </s:iterator>
	    <table align="center">
	     <tr>
				<td align="center">
					<s:submit disabled="flag" value="检查产品入库" />
				</td>
				<td align="center">
					<s:reset value="重置" />
				</td>
			</tr>
			</table>
  </s:form>

  </table>
</body>
</html>