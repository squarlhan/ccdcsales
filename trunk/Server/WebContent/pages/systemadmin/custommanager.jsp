<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户管理</title>
<style type="text/css" media="all"> 

@import "/Server/css/main.css";
@import "/Server/css/css.css";
</style>
<script language="javascript"> 
function urlpara_modify(uid,uname,ushdz,uphone) 
{
    location.href= encodeURI("admincustommanager!modify.action?id="+uid+"&customName="+uname+"&shdz="+ushdz+"&phone="+uphone); 
} 
function confirm_modify(rownum)
{
	var cf = confirm("确认修改？");
	if(cf)
	{
		var uid = (document.getElementById("id_"+rownum)).value;
		var uname = (document.getElementById("customName_"+rownum)).value;
		var ushdz = (document.getElementById("shdz_"+rownum)).value;
		var uphone = (document.getElementById("phone_"+rownum)).value;
		if(uname.replace(/(^\s*)|(\s*$)/g,"") == "")
			alert("客户名不能为空 ");
		else
			if(ushdz.replace(/(^\s*)|(\s*$)/g,"") == "")
				alert("收货地址不能为空 ");
			else
				if(uphone.replace(/(^\s*)|(\s*$)/g,"")!="")
					if(isNaN(uphone))
						alert("手机号必须为数字");
					else
						if(uphone.length!=11)
							alert("手机号必须为11位");
						else
							urlpara_modify(uid,uname,ushdz,uphone);
				else
					urlpara_modify(uid,uname,ushdz,uphone);
		}
}
function urlpara_delete(uid) 
{
    location.href= encodeURI("admincustommanager!delete.action?id="+uid); 
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
		var newname = (document.getElementById("newname")).value;
		var newshdz = (document.getElementById("newshdz")).value;
		var newuphone = (document.getElementById("newuphone")).value;
		if(newname.replace(/(^\s*)|(\s*$)/g,"") == "")
		{
			alert("客户名不能为空 ");
			return false;
		}
		else
			if(newshdz.replace(/(^\s*)|(\s*$)/g,"") == ""){
				alert("收货地址不能为空 ");
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
<h2 align="center">客户管理</h2>

  <s:form id="myform" name="myform" action="admincustommanager" theme="simple">
  	<table class="list_table" id="tb" align="center" width="640">
		<tr bgcolor="#4A708B">
		    <th width="8%">客户ID</th>
		    <th width="12%">客户名</th>
		    <th width="30%">收货地址</th>
		    <th width="15%">手机号</th>
		    <th width="30%">操作</th>
		</tr>
		<s:iterator id="result" value="resultList" status="index">	
     	<tr bgcolor="<s:if test="#index.odd == true">#ffffff</s:if><s:else>#EDEDED</s:else>">
   
        <td><s:textfield size = "5" readonly="true" value="%{#result.id}" id="id_%{#index.index}" name="id_%{#index.index}"/></td>

        <td><s:textfield size = "10" id="customName_%{#index.index}" value="%{#result.customName}" name="customName_%{#index.index}"/></td>
        
         <td><s:textfield size = "25" id="shdz_%{#index.index}" value="%{#result.shdz}" name="shdz_%{#index.index}"/></td>
        
        <td><s:textfield size = "12" id="phone_%{#index.index}" value="%{#result.phone}" name="phone_%{#index.index}"/></td>
         
        <td align="center" width="">
        	<a href="javascript:confirm_modify(${index.index})">修改</a>
        	&nbsp 
        	<a href="javascript:confirm_delete(${index.index})">删除</a> 
        </td>
      </tr>
      </s:iterator>

	  </table>
</s:form>
<s:form theme="simple" onsubmit="return confirm_add()">	  
		 <table class="list_table" align="center">
		 	<tr>
		 	<td><label>登录名:</label></td>
	        <td><s:textfield id="newname" name="newname"/></td>
	      </tr>
	      <tr>
	      	<td><label>收货地址:</label></td>
	         <td>
	         	<s:textfield id="newshdz" name="newshdz"/>
         	</td>
	      </tr>
	      <tr>
	      	<td><label>手机号:</label></td>
	        <td><s:textfield id="newuphone" name="newuphone"/></td>
	      </tr>
	      <tr>
	         <td><label></label></td>
	      	<td align="center"><s:submit value="添加" action="admincustommanager!add"/> </td>
	      </tr>
	      
		</table>
  </s:form>
</body>
</html>