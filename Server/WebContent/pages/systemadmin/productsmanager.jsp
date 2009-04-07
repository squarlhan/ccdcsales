<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品管理</title>
<style type="text/css" media="all"> 
@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript"> 
function urlpara_modify(pid,pname) 
{
    location.href= encodeURI("adminproductsmanager!modify.action?id="+pid+"&name="+pname); 
} 
function confirm_modify(rownum)
{
	var cf = confirm("确认修改？");
	if(cf)
	{
		var pid = (document.getElementById("pid_"+rownum)).value;
		var pname = (document.getElementById("pname_"+rownum)).value;
		if(pname.replace(/(^\s*)|(\s*$)/g,"") == "")
			alert("产品名不能为空 ");
		else
			urlpara_modify(pid,pname);
	}
}
function urlpara_delete(pid) 
{
    location.href= encodeURI("adminproductsmanager!delete.action?id="+pid); 
} 
function confirm_delete(rownum)
{
	var cf = confirm("确认删除？");
	if(cf)
	{
		var pid = (document.getElementById("pid_"+rownum)).value;
		urlpara_delete(pid);
	}
}
function confirm_add()
{
	var cf = confirm("确认添加？");
	if(cf)
	{
		var newpname = (document.getElementById("newpname")).value;
		if(newpname.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
			alert("产品名不能为空 ");
			return false;
		}
		else
			return true;
	}else
		return false;
}

</script>
</head>
<body>
<h2 align="center">产品管理</h2>
  
  <s:form id="myform" name="myform" action="adminproductsmanager" theme="simple">
  	<table class="list_table" id="tb" align="center" width="640">
		<tr bgcolor="#4A708B">
		    <th width="120">产品标识</th>
		    <th width="120">产品名称</th>
		    <th width="120">执行操作</th>
		</tr>
		<s:iterator id="result" value="resultList" status="index">	
     	<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
   
        <td><s:textfield readonly="true" value="%{#result.id}" id="pid_%{#index.index}" name="pid_%{#index.index}"/></td>

        <td><s:textfield id="pname_%{#index.index}" value="%{#result.name}" name="pname_%{#index.index}"/></td>
        <td align="center" width="">

        	<a  href="javascript:confirm_modify(${index.index})">修改</a>
        	&nbsp;
        	<a href="javascript:confirm_delete(${index.index})">删除</a> 
        </td>
      </tr>
      </s:iterator>
       </table>
  </s:form>
  <s:form onsubmit="return confirm_add()">
	 
		 <table class="list_table" align="center">
		 	<tr>
		 	<td><label>产品名称:</label></td>
	        <td><s:textfield id="newpname" name="newpname" theme="simple"/></td>     
	      </tr>
	      <tr>
	        <td><s:submit value="添加" action="adminproductsmanager!add"/></td>     
	      </tr>
		</table>
  </s:form>
</body>
</html>