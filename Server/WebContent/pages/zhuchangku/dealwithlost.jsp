<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>驻厂库损耗产品处理</title>
<style type="text/css" media="all"> 
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language = "javascript" >
	function urlpara(pch,pid,spid,lostvalue) 
	{
	    location.href= "zckdealwithlost.action?pch="+pch+"&pid="+pid+"&spid="+spid+"&lostvalue="+lostvalue; 
	} 
	function confirmbtn(rownum)
	{
		var pch = (document.getElementById("pch_"+rownum)).value;
		var pid = (document.getElementById("pid_"+rownum)).value;
		var spid = (document.getElementById("spid_"+rownum)).value;
		var lost = (document.getElementById("lost_"+rownum)).value;
		if(lost.replace(/(^\s*)|(\s*$)/g,"")=="")
		{
			alert("请填写损耗数量");
			return;
		}
		if(isNaN(lost))
		{
			alert("只允许输入数字！");
			return;
		}
		if(confirm("确认损耗？"))
		{
			urlpara(pch,pid,spid,lost);
			return;
		}
	}
	
</script>
</head>
<body>
<h2>驻厂库损耗产品处理</h2>
<h3>驻厂库所有产品明细</h3>
<s:form id="myform" action="zckdealwithlost" theme="simple" >
<table class="list_table" align="center" width="640">
  <tr bgcolor="#4A708B">
	<th width="20%">批次号</th>
	<th style="display:none">产品ID</th>
	<th width="20%">产品名</th>
	<th style="display:none">规格ID</th>
	<th width="20%">规格</th>
	<th width="20%">损耗</th>
	<th width="20%">操作</th>
  </tr>
  <s:iterator id="result" value="resultList" status="index">	
    <tr bgcolor='<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>'>
	  <td align="center">
		<s:textfield readonly="true" size="10" id="pch_%{#index.index}" name="pch_%{#index.index}" value="%{#result.id.pch}" /> 
	  </td>
	  <td align="center" style="display:none">
		<s:textfield readonly="true" size="5" id="pid_%{#index.index}" name="pid_%{#index.index}" value="%{#result.products.id}" />
	  </td>
	  <td align="center" >
		<s:textfield readonly="true" size="10" id="pname_%{#index.index}"  value="%{#result.products.name}" />
	  </td>
	   <td align="center" style="display:none">
		<s:textfield readonly="true" size="5" id="spid_%{#index.index}" name="spid_%{#index.index}" value="%{#result.specifications.id}" />
	  </td>
	  <td align="center">
		<s:textfield readonly="true" size="10" id="spname_%{#index.index}"  value="%{#result.specifications.displayName}" />
	  </td>
	  <td align="center">
		<s:textfield size="5" id="lost_%{#index.index}" name="lost_%{#index.index}"/>
	  </td>
	  <td align="center"><a href="javascript:confirmbtn(${index.index })">损耗</a></td>
    </tr>
  </s:iterator>
</table>
</s:form>
</body>
</html>