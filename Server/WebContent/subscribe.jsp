<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="org.springframework.security.context.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>短信订阅设置</title>
<style type="text/css" media="all">
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<s:head/>
<script language="javascript"> 
function confirm_update()  
{
	var cf = confirm("确认修改？");
	if(cf)
	{
        return true;
	}
	return false;
}
</script>
</head>
<body>
<h2>短信订阅设置页面</h2>
<s:form  onsubmit="return confirm_update()" theme="simple">
<table class="list_table" width="100%" align="center" style="margin-left:0">
    <tr bgcolor="#4A708B">
		<th width="10%">产品名称</th>
		<th width="12%">销售移库单</th>
		<th width="13%">销售移库装车</th>
		<th width="13%">销售移库入库</th>
		<th width="12%">销售发货单</th>
		<th width="13%">销售发货入库</th>
		<th width="13%">储运移库装车</th>
		<th width="13%">储运移库入库</th>
	</tr>
	<s:iterator id="result" value="resultList" status="index">	
     	<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
   
        <td><s:property value="#result.smm.product.name"/></td>

        <td><s:checkbox name="xsyk1[%{#index.index}]" id="xsyk1[%{#index.index}]" value="result[%{#index.index}].xsyk1"/></td>
        
        <td><s:checkbox name="xsyk2[%{#index.index}]" id="xsyk2[%{#index.index}]" value="result[%{#index.index}].xsyk2"/></td>
         
        <td><s:checkbox name="xsyk3[%{#index.index}]" id="xsyk3[%{#index.index}]" value="result[%{#index.index}].xsyk3"/></td>
        	
        <td><s:checkbox name="xsfh1[%{#index.index}]" id="xsfh1[%{#index.index}]" value="result[%{#index.index}].xsfh1"/></td>
        
        <td><s:checkbox name="xsfh2[%{#index.index}]" id="xsfh2[%{#index.index}]" value="result[%{#index.index}].xsfh2"/></td>
        
        <td><s:checkbox name="cycyk1[%{#index.index}]" id="cycyk1[%{#index.index}]" value="result[%{#index.index}].cycyk1"/></td>
        
        <td><s:checkbox name="cycyk2[%{#index.index}]" id="cycyk2[%{#index.index}]" value="result[%{#index.index}].cycyk2"/></td>
      </tr>
      </s:iterator>
	</table>
	<br/>
	<table width="150" align="center">
	<tr>
	<td width="80" align="center"><s:submit value="更改" theme="simple" action="userupdateUser"/>
	</td>
	
	<td width="80" align="center"> <s:reset value="取消"
		theme="simple" /></td></tr></table>
</s:form>
</body>
</html>