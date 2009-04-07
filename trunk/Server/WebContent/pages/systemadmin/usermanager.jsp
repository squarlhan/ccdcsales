<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<style type="text/css" media="all"> 

@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript"> 
function urlpara_modify(uid,uname,upassword,uphone,udes) 
{
    location.href= encodeURI("adminusermanager!modify.action?id="+uid+"&name="+uname+"&password="+upassword+"&phone="+uphone+"&description="+udes); 
} 
function confirm_reset(rownum)
{
	var cf = confirm("确认重置？");
	if(cf)
	{
		var uid = (document.getElementById("id_"+rownum)).value;
		var uname = (document.getElementById("name_"+rownum)).value;
		var upassword = (document.getElementById("name_"+rownum)).value;
		var uphone = (document.getElementById("phone_"+rownum)).value;
		var udes = (document.getElementById("description_"+rownum)).value;
		if(uname.replace(/(^\s*)|(\s*$)/g,"") == "")
			alert("登陆名不能为空 ");
		else
			if(uphone.replace(/(^\s*)|(\s*$)/g,"")!="")
				if(isNaN(uphone))
					alert("手机号必须为数字");
				else
					if(uphone.length!=11)
						alert("手机号必须为11位");
					else
						urlpara_modify(uid,uname,upassword,uphone,udes);
			else
				urlpara_modify(uid,uname,upassword,uphone,udes);
	}
}
function confirm_modify(rownum)
{
	var cf = confirm("确认修改？");
	if(cf)
	{
		var uid = (document.getElementById("id_"+rownum)).value;
		var uname = (document.getElementById("name_"+rownum)).value;
		var upassword = (document.getElementById("password_"+rownum)).value;
		var uphone = (document.getElementById("phone_"+rownum)).value;
		var udes = (document.getElementById("description_"+rownum)).value;
		if(uname.replace(/(^\s*)|(\s*$)/g,"") == "")
			alert("登陆名不能为空 ");
		else
			if(uphone.replace(/(^\s*)|(\s*$)/g,"")!="")
				if(isNaN(uphone))
					alert("手机号必须为数字");
				else
					if(uphone.length!=11)
						alert("手机号必须为11位");
					else
						urlpara_modify(uid,uname,upassword,uphone,udes);
			else
				urlpara_modify(uid,uname,upassword,uphone,udes);
	}
}
function urlpara_delete(uid) 
{
    location.href= encodeURI("adminusermanager!delete.action?id="+uid); 
} 
function confirm_delete(rownum)
{
	var cf = confirm("确认删除？");
	if(cf)
	{
		var uid = (document.getElementById("id_"+rownum)).value;
		urlpara_delete(uid);
	}
}
function confirm_add()
{
	var cf = confirm("确认添加？");
	if(cf)
	{
		var newuname = (document.getElementById("newuname")).value;
		var newupass = (document.getElementById("newupass")).value;
		var newuphone = (document.getElementById("newuphone")).value;
		var newudes = (document.getElementById("newudes")).value;
		if(newuname.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
			alert("登陆名不能为空 ");
			return false;
		}
		else
			if(newupass.replace(/(^\s*)|(\s*$)/g,"") == "")
			{
				alert("登录密码不能为空");
				return false;
			}
			else{
				if(newuphone.replace(/(^\s*)|(\s*$)/g,"")!="")
					if(isNaN(newuphone)){
						alert("手机号必须为数字");
						return false;
					}
					else
						if(newuphone.length!=11){
							alert("手机号必须为11位");
							return false;
						}
						else
							return true;
				return true;
			}
	}else
		return false;
}
</script>
</head>
<body>
<h2 align="center">用户管理</h2>

  <s:form id="myform" name="myform" action="adminusermanager" theme="simple">
  	<table  class="list_table" id="tb" align="center" width="640">
		<tr bgcolor="#4A708B">
		    <th width="8%">用户ID</th>
		    <th width="12%">登录名</th>
		    <th style="display:none">密码</th>
		    <th width="15%">手机号</th>
		    <th width="15%">描述</th>
		    <th width="35%">操作</th>
		</tr>
		<s:iterator id="result" value="resultList" status="index">	
     	<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
   
        <td><s:textfield size = "5" readonly="true" value="%{#result.id}" id="id_%{#index.index}" name="id_%{#index.index}"/></td>

        <td><s:textfield size = "10" id="name_%{#index.index}" value="%{#result.name}" name="name_%{#index.index}"/></td>
        
         <td style="display:none"><s:textfield size = "10" id="password_%{#index.index}" value="%{#result.password}" name="password_%{#index.index}"/></td>
        
        <td><s:textfield size = "12" id="phone_%{#index.index}" value="%{#result.phone}" name="phone_%{#index.index}"/></td>
         
         <td>
         	<s:textfield size = "15" value="%{#result.description}" id="description_%{#index.index}" name="description_%{#index.index}" />
         </td>
         
        <td align="center" width="">
        	<a href="javascript:confirm_modify(${index.index})">修改</a>
        	&nbsp 
        	<a href="javascript:confirm_delete(${index.index})">删除</a>
        	&nbsp 
        	<a href="javascript:confirm_reset(${index.index})">重置</a> 
        </td>
      </tr>
      </s:iterator>

	  </table>
</s:form>
<s:form theme="simple" onsubmit="return confirm_add()">	  
		 <table  class="list_table" align="center">
		 	<tr>
		 	<td><label>登录名:</label></td>
	        <td><s:textfield id="newuname" name="newuname"/></td>
	      </tr>
	      <tr>
	      	<td><label>密码:</label></td>
	         <td>
	         	<s:textfield id="newupass" name="newupass"/>
         	</td>
	      </tr>
	      <tr>
	      	<td><label>手机号:</label></td>
	        <td><s:textfield id="newuphone" name="newuphone"/></td>
	      </tr>
	      <tr>
	      	<td><label>描述:</label></td>
	        <td><s:textfield id="newudes" name="newudes"/></td>
	      </tr>
	      <tr>
	        <td><label></label></td>
	      	<td align="center"><s:submit value="添加" action="adminusermanager!add"/> </td>
	      </tr>
	      
		</table>
  </s:form>
</body>
</html>