<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组管理</title>
<style type="text/css" media="all"> 

@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript"> 
function urlpara_modify(uid,uname,udes) 
{
    location.href= encodeURI("admingroupmanager!modify.action?id="+uid+"&name="+uname+"&description="+udes); 
} 
function confirm_modify(rownum)
{
	var cf = confirm("确认修改？");
	if(cf)
	{
		var uid = (document.getElementById("id_"+rownum)).value;
		var uname = (document.getElementById("name_"+rownum)).value;
		var udes = (document.getElementById("description_"+rownum)).value;
		if(uname.replace(/(^\s*)|(\s*$)/g,"") == "")
			alert("组名不能为空 ");
		else
			urlpara_modify(uid,uname,udes);
	}
}
function urlpara_delete(gid) 
{
    location.href= encodeURI("admingroupmanager!delete.action?id="+gid); 
} 
function confirm_delete(rownum)
{
	var cf = confirm("确认删除？");
	if(cf)
	{
		var gid = (document.getElementById("id_"+rownum)).value;
		urlpara_delete(gid);
	}
}
function confirm_add()
{
	var cf = confirm("确认添加？");
	if(cf)
	{
		var newuname = (document.getElementById("newuname")).value;
		if(newuname.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
			alert("组名不能为空 ");
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
<h2 align="center">组管理</h2>

  <s:form id="myform" name="myform" action="admingroupmanager" theme="simple">
  	<table class="list_table" id="tb" align="center" width="640">
		<tr bgcolor="#4A708B">
		    <th width="8%">组ID</th>
		    <th width="12%">组名称</th>
		    <th width="15%">描述</th>
		    <th width="30%">操作</th>
		</tr>
		<s:iterator id="result" value="resultList" status="index">	
     	<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
   
        <td><s:textfield size = "5" readonly="true" value="%{#result.id}" id="id_%{#index.index}" name="id_%{#index.index}"/></td>

        <td><s:textfield size = "10" id="name_%{#index.index}" value="%{#result.name}" name="name_%{#index.index}"/></td>
        
        <td><s:textfield size = "15" value="%{#result.description}" id="description_%{#index.index}" name="description_%{#index.index}" /></td>
         
        <td align="center" width="">
        	<a href="javascript:confirm_modify(${index.index})">修改</a>
        	&nbsp; 
        	<a href="javascript:confirm_delete(${index.index})">删除</a> 
        </td>
      </tr>
      </s:iterator>
	</table>      
</s:form>
  <s:form theme="simple" onsubmit="return confirm_add()">
		 <table class="list_table" align="center">
		 	<tr>
		 	<td><label>组名称:</label></td>
	        <td><s:textfield id="newuname" name="newuname"/></td>
	      </tr>
	      <tr>
	      	<td><label>描述:</label></td>
	        <td><s:textfield id="newudes" name="newudes"/></td>
	      </tr>
	      <tr>
	        <td><label></label></td>
	      	<td align="center"><s:submit value="添加" action="admingroupmanager!add"/> </td>
	      </tr>
	      
		</table>
  </s:form>
</body>
</html>