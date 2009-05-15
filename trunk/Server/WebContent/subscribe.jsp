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
		document.myform.action = "usermodifydy.action";
        return true;
	}
	return false;
}

//设置checkbox是否勾上
var xsyk1s = new Array(
	<s:iterator id="result" value="resultList">
		"<s:property value='#result.xsyk1'/>",
	</s:iterator>
	0);
var xsyk2s = new Array(
		<s:iterator id="result" value="resultList">
			"<s:property value='#result.xsyk2'/>",
		</s:iterator>
		0);
var xsyk3s = new Array(
		<s:iterator id="result" value="resultList">
			"<s:property value='#result.xsyk3'/>",
		</s:iterator>
		0);
var xsfh1s = new Array(
		<s:iterator id="result" value="resultList">
			"<s:property value='#result.xsfh1'/>",
		</s:iterator>
		0);
var xsfh2s = new Array(
		<s:iterator id="result" value="resultList">
			"<s:property value='#result.xsfh2'/>",
		</s:iterator>
		0);
var cycyk1s = new Array(
		<s:iterator id="result" value="resultList">
			"<s:property value='#result.cycyk1'/>",
		</s:iterator>
		0);
var cycyk2s = new Array(
		<s:iterator id="result" value="resultList">
			"<s:property value='#result.cycyk2'/>",
		</s:iterator>
		0);
function setcheck()
{
	for(var a=0;a<xsyk1s.length-1;a++){
    	var xsyk1 = document.getElementById("xsyk1["+a+"]");
    	var xsyk2 = document.getElementById("xsyk2["+a+"]");
    	var xsyk3 = document.getElementById("xsyk3["+a+"]");
    	var xsfh1 = document.getElementById("xsfh1["+a+"]");
    	var xsfh2 = document.getElementById("xsfh2["+a+"]");
    	var cycyk1 = document.getElementById("cycyk1["+a+"]");
    	var cycyk2 = document.getElementById("cycyk2["+a+"]");
    	
    	if(xsyk1s[a]=="true"){xsyk1.checked="checked";}
    	else {xsyk1.checked=null;}
    	if(xsyk2s[a]=="true"){xsyk2.checked="checked";}
    	else {xsyk2.checked=null;}
    	if(xsyk3s[a]=="true"){xsyk3.checked="checked";}
    	else {xsyk3.checked=null;}
    	if(xsfh1s[a]=="true"){xsfh1.checked="checked";}
    	else {xsfh1.checked=null;}
    	if(xsfh2s[a]=="true"){xsfh2.checked="checked";}
    	else {xsfh2.checked=null;}
    	if(cycyk1s[a]=="true"){cycyk1.checked="checked";}
    	else {cycyk1.checked=null;}
    	if(cycyk2s[a]=="true"){cycyk2.checked="checked";}
    	else {cycyk2.checked=null;}
    }
}
var flag = new Array("<s:property value='flag'/>",0); 

function checkauth()
{
	if(flag[0]=="false")
		{
	        var rows = document.getElementById("tb").rows;  
	        for(var i=0;i<rows.length;i++)
		        {
	        	    rows[i].cells[6].style.display = "none";
	        	    rows[i].cells[7].style.display = "none";
		        }
	       
	    }
}

</script>
</head>
<body>
<h2>短信订阅设置页面</h2>
<s:form id="myform" onsubmit="return confirm_update()" theme="simple">
<table class="list_table" id="tb" width="100%" align="center" style="margin-left:0">
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

        <td><s:checkbox name="xsyk1[%{#index.index}]" id="xsyk1[%{#index.index}]"/></td>
        
        <td><s:checkbox name="xsyk2[%{#index.index}]" id="xsyk2[%{#index.index}]"/></td>
         
        <td><s:checkbox name="xsyk3[%{#index.index}]" id="xsyk3[%{#index.index}]"/></td>
        	
        <td><s:checkbox name="xsfh1[%{#index.index}]" id="xsfh1[%{#index.index}]"/></td>
        
        <td><s:checkbox name="xsfh2[%{#index.index}]" id="xsfh2[%{#index.index}]"/></td>
        
        <td><s:checkbox name="cycyk1[%{#index.index}]" id="cycyk1[%{#index.index}]"/></td>
        
        <td><s:checkbox name="cycyk2[%{#index.index}]" id="cycyk2[%{#index.index}]"/></td>
      </tr>
      </s:iterator>
	</table>
	<br/>
	<table width="150" align="center">
	<tr>
	<td width="80" align="center"><s:submit value="更改" theme="simple"/>
	</td>
	
	<td width="80" align="center"> <s:reset value="取消"
		theme="simple" /></td></tr></table>
</s:form>
<script language="javascript">
    setcheck();
    checkauth();
</script>
</body>
</html>